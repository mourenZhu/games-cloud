package cn.zhumouren.games.cloud.moment.service.impl;

import cn.zhumouren.games.cloud.moment.entity.Moment;
import cn.zhumouren.games.cloud.moment.mapper.MomentMapper;
import cn.zhumouren.games.cloud.moment.service.IMomentService;
import cn.zhumouren.games.cloud.moment.service.ILikeService;
import cn.zhumouren.games.cloud.moment.service.IForwardMomentService;
import cn.zhumouren.games.cloud.moment.utils.JwtUtils;
import cn.zhumouren.games.cloud.moment.utils.ListUtils;
import cn.zhumouren.games.cloud.moment.utils.UrlUtils;
import cn.zhumouren.games.cloud.moment.vo.MomentVO;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
@Service
public class MomentServiceImpl extends ServiceImpl<MomentMapper, Moment> implements IMomentService {

    @Autowired
    private MomentMapper momentMapper;

    @Autowired
    private ILikeService likesService;

    @Autowired
    private IForwardMomentService forwardMomentService;

    @Override
    @Transactional
    public boolean postMoment(Moment moment) {
        momentMapper.insert(moment);
        moment.setUrl(UrlUtils.creatContentUrl(moment.getUsername(), moment.getId()));

        if (moment.getParentId() == null || "".equals(moment.getParentId())) {
            moment.setParentPaths("[" + moment.getId() + "]");
        } else {
            Moment parentMoment = momentMapper.selectById(moment.getParentId());
            moment.setParentUsername(parentMoment.getUsername());
            moment.setParentPaths(
                    parentMoment.getParentPaths()
                            .replace("]", "," + moment.getId() + "]"));
        }

        return SqlHelper.retBool(momentMapper.updateById(moment));
    }

    @Override
    public boolean deleteMoment(Moment moment, String token) {
        UpdateWrapper<Moment> updateWrapper = new UpdateWrapper();
        String username = JwtUtils.getString(token, "user_name");
        updateWrapper.eq("id", moment.getId()).eq("username", username).set("is_delete", 0);

        return SqlHelper.retBool(momentMapper.update(null, updateWrapper));
    }

    @Override
    public IPage<Moment> getMomentLinkPage(Page<Moment> page, Long momentId) {
        List<Long> parentList = listMomentParentPaths(momentId);
        return momentMapper.getMomentLinkPage(page, momentId, parentList);
    }

    @Override
    public IPage<MomentVO> getMomentVOLinkPage(Page<Moment> page, Long momentId) {

        IPage<MomentVO> momentVOPage = new Page<>();

        List<MomentVO> momentVOList = listMomentVOLinks(page, momentId);


        momentVOPage.setRecords(momentVOList);
        momentVOPage.setPages(page.getPages());
        momentVOPage.setCurrent(page.getCurrent());
        momentVOPage.setSize(page.getSize());
        momentVOPage.setTotal(page.getTotal());
        return momentVOPage;
    }

    @Override
    public List<MomentVO> listMomentVOs(List<Long> momentIdList) {
        List<MomentVO> momentVOList;
        List<Moment> momentList = momentMapper.listMoment(momentIdList);
        momentVOList = getContentVOListByContentList(momentList);
        return momentVOList;
    }

    /**
     * 通过momentList获得momentIdList（其实就是简单的分离一下）
     *
     * @param momentList
     * @return
     */
    private List<Long> listMomentIdsByContentList(List<Moment> momentList) {
        List<Long> idList = new ArrayList<>();
        for (Moment c : momentList) {
            idList.add(c.getId());
        }
        return idList;
    }

    /**
     * 把最基础的moment对象封装成VO对象，代码简单，但内容较多，分离一下代码更美观。
     *
     * @param page
     * @param momentId
     * @return
     */
    private List<MomentVO> listMomentVOLinks(Page<Moment> page, Long momentId) {
        List<MomentVO> momentVOList;
        List<Long> parentList = listMomentParentPaths(momentId);
        IPage<Moment> momentLink = momentMapper.getMomentLinkPage(page, momentId, parentList);
        List<Moment> momentList = momentLink.getRecords();
        momentVOList = getContentVOListByContentList(momentList);
        return momentVOList;
    }

    /**
     * 通过获得momentList把moment包装成momentVO，因为有多个方法要共用这一段代码，所以分离一下。
     * @param momentList
     * @return
     */
    private List<MomentVO> getContentVOListByContentList(List<Moment> momentList) {

        List<MomentVO> momentVOList = new LinkedList<>();

        List<Long> momentIdList = listMomentIdsByContentList(momentList);

        Map<Long, Integer> momentLikeMap = likesService.getMomentLikeNumsMap(momentIdList);
        Map<Long, Integer> momentCommentMap = getMomentCommentNumsMap(momentIdList);
        Map<Long, Integer> momentForwardMap = forwardMomentService.getMomentForwardNumsMap(momentIdList);
        Map<Long, Integer> momentQuoteMap = getMomentQuoteNumsMap(momentIdList);

        for (Moment c : momentList) {

            Integer commentNums = momentCommentMap.get(c.getId());
            Integer likeNums = momentLikeMap.get(c.getId());
            Integer forwardNums = momentForwardMap.get(c.getId());
            Integer quoteNums = momentQuoteMap.get(c.getId());

            MomentVO momentVO = new MomentVO();
            momentVO.setMoment(c);
            if (commentNums != null) {
                momentVO.setCommentNums(commentNums);
            } else {
                momentVO.setCommentNums(0);
            }
            if (likeNums != null) {
                momentVO.setLikeNums(likeNums);
            } else {
                momentVO.setLikeNums(0);
            }
            if (forwardNums != null) {
                momentVO.setForwardNums(forwardNums);
            } else {
                momentVO.setForwardNums(0);
            }
            if (quoteNums != null) {
                momentVO.setQuoteNums(quoteNums);
            } else {
                momentVO.setQuoteNums(0);
            }

            momentVOList.add(momentVO);
        }

        return momentVOList;
    }


    @Override
    public List<Long> listMomentParentPaths(Long momentId) {
        Moment moment = momentMapper.selectById(momentId);
        String paths = moment.getParentPaths();
        return ListUtils.getListByString(paths);
    }


    @Override
    public Map<Long, Integer> getMomentCommentNumsMap(List<Long> momentIdList) {
        return momentMapper.getMomentCommentMap(momentIdList);
    }

    @Override
    public Map<Long, Integer> getMomentQuoteNumsMap(List<Long> momentIdList) {
        return momentMapper.getMomentQuoteMap(momentIdList);
    }

    @Override
    public IPage<String> getMomentQuoteUsernamePage(Page<String> page, Long momentId) {
        return momentMapper.getMomentQuoteUsernamePage(page, momentId);
    }
}
