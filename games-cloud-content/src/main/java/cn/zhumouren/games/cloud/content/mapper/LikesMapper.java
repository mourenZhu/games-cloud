package cn.zhumouren.games.cloud.content.mapper;

import cn.zhumouren.games.cloud.content.entity.Likes;
import cn.zhumouren.games.cloud.content.vo.ContentVO;
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
public interface LikesMapper extends BaseMapper<Likes> {

    /**
     * 获得内容点赞数map
     *
     * @param contentIdList
     * @return
     */
    Map<Long, Integer> getContentLikeNumsMap(@Param("contentIdList") List<Long> contentIdList);

    /**
     * 获得点赞了该条内容的用户
     *
     * @param page
     * @param contentId
     * @return
     */
    IPage<String> getContentLikeUsernames(Page<String> page, @Param("contentId") Long contentId);

    /**
     * 获得用户点赞了的内容
     *
     * @param page
     * @param username
     * @return
     */
    IPage<Long> getUserLikeContentIdPage(Page<Long> page, @Param("username") String username);
}
