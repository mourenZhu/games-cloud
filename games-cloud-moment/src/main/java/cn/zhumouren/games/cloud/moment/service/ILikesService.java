package cn.zhumouren.games.cloud.moment.service;

import cn.zhumouren.games.cloud.moment.entity.Content;
import cn.zhumouren.games.cloud.moment.entity.Likes;
import cn.zhumouren.games.cloud.moment.vo.ContentVO;
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
public interface ILikesService extends IService<Likes> {

    /**
     * 获得内容点赞数map
     *
     * @param contentIdList
     * @return
     */
    Map<Long, Integer> getContentLikeNumsMap(List<Long> contentIdList);

    /**
     * 获得点赞了该条内容的用户
     *
     * @param page
     * @param contentId
     * @return
     */
    IPage<String> getContentLikeUsernames(Page<String> page, Long contentId);

    /**
     * 获得用户点赞了的内容
     *
     * @param page
     * @param username
     * @return
     */
    IPage<ContentVO> getUserLikeContentVOPage(Page<Content> page, String username);
}
