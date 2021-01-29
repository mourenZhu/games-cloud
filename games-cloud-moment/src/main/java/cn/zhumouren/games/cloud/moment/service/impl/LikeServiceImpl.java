package cn.zhumouren.games.cloud.moment.service.impl;

import cn.zhumouren.games.cloud.moment.entity.Moment;
import cn.zhumouren.games.cloud.moment.entity.Like;
import cn.zhumouren.games.cloud.moment.mapper.LikeMapper;
import cn.zhumouren.games.cloud.moment.service.IMomentService;
import cn.zhumouren.games.cloud.moment.service.ILikeService;
import cn.zhumouren.games.cloud.moment.vo.MomentVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements ILikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private IMomentService momentService;

    @Override
    public Map<Long, Integer> getMomentLikeNumsMap(List<Long> momentIdList) {
        return likeMapper.getMomentLikeNumsMap(momentIdList);
    }

    @Override
    public IPage<String> getMomentLikeUsernamePage(Page<String> page, Long momentId) {
        return likeMapper.getMomentLikeUsernamePage(page, momentId);
    }

    @Override
    public IPage<MomentVO> getUserLikeMomentVOPage(Page<Moment> page, String username) {
        Page<Long> momentIdPage = new Page<>();
        momentIdPage.setSize(page.getSize());
        momentIdPage.setCurrent(page.getCurrent());
        momentIdPage.setTotal(page.getTotal());
        momentIdPage.setPages(page.getPages());

        IPage<Long> userLikeMomentIdPage = likeMapper.getUserLikeMomentIdPage(momentIdPage, username);
        List<Long> momentIdList = userLikeMomentIdPage.getRecords();

        IPage<MomentVO> momentVOIPage = new Page<>();
        momentVOIPage.setRecords(momentService.listMomentVOs(momentIdList));
        momentVOIPage.setPages(userLikeMomentIdPage.getPages());
        momentVOIPage.setSize(userLikeMomentIdPage.getSize());
        momentVOIPage.setTotal(userLikeMomentIdPage.getTotal());
        momentVOIPage.setCurrent(userLikeMomentIdPage.getCurrent());
        return momentVOIPage;
    }
}
