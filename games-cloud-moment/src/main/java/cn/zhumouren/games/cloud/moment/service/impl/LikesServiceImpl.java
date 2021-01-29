package cn.zhumouren.games.cloud.moment.service.impl;

import cn.zhumouren.games.cloud.moment.entity.Moment;
import cn.zhumouren.games.cloud.moment.entity.Likes;
import cn.zhumouren.games.cloud.moment.mapper.LikesMapper;
import cn.zhumouren.games.cloud.moment.service.IMomentService;
import cn.zhumouren.games.cloud.moment.service.ILikesService;
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
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements ILikesService {

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private IMomentService momentService;

    @Override
    public Map<Long, Integer> getMomentLikeNumsMap(List<Long> momentIdList) {
        return likesMapper.getMomentLikeNumsMap(momentIdList);
    }

    @Override
    public IPage<String> getMomentLikeUsernamePage(Page<String> page, Long momentId) {
        return likesMapper.getMomentLikeUsernamePage(page, momentId);
    }

    @Override
    public IPage<MomentVO> getUserLikeMomentVOPage(Page<Moment> page, String username) {
        Page<Long> momentIdPage = new Page<>();
        momentIdPage.setSize(page.getSize());
        momentIdPage.setCurrent(page.getCurrent());
        momentIdPage.setTotal(page.getTotal());
        momentIdPage.setPages(page.getPages());

        IPage<Long> userLikeMomentIdPage = likesMapper.getUserLikeMomentIdPage(momentIdPage, username);
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
