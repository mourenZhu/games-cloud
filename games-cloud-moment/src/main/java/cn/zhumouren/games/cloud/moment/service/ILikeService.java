package cn.zhumouren.games.cloud.moment.service;

import cn.zhumouren.games.cloud.moment.entity.Moment;
import cn.zhumouren.games.cloud.moment.entity.Like;
import cn.zhumouren.games.cloud.moment.vo.MomentVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
public interface ILikeService extends IService<Like> {

    /**
     * 获得内容点赞数map
     *
     * @param momentIdList
     * @return
     */
    Map<Long, Integer> getMomentLikeNumsMap(List<Long> momentIdList);

    /**
     * 获得点赞了该条内容的用户
     *
     * @param page
     * @param momentId
     * @return
     */
    IPage<String> getMomentLikeUsernamePage(Page<String> page, Long momentId);

    /**
     * 获得用户点赞了的内容
     *
     * @param page
     * @param username
     * @return
     */
    IPage<MomentVO> getUserLikeMomentVOPage(Page<Moment> page, String username);
}
