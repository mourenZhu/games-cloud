package cn.zhumouren.games.cloud.moment.mapper;

import cn.zhumouren.games.cloud.moment.entity.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
@Repository
public interface LikeMapper extends BaseMapper<Like> {

    /**
     * 获得内容点赞数map
     *
     * @param momentIdList
     * @return
     */
    Map<Long, Integer> getMomentLikeNumsMap(@Param("momentIdList") List<Long> momentIdList);

    /**
     * 获得点赞了该条内容的用户
     *
     * @param page
     * @param momentId
     * @return
     */
    IPage<String> getMomentLikeUsernamePage(Page<String> page, @Param("momentId") Long momentId);

    /**
     * 获得用户点赞了的内容
     *
     * @param page
     * @param username
     * @return
     */
    IPage<Long> getUserLikeMomentIdPage(Page<Long> page, @Param("username") String username);
}
