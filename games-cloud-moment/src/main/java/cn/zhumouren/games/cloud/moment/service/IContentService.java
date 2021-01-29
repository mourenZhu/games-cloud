package cn.zhumouren.games.cloud.moment.service;

import cn.zhumouren.games.cloud.moment.entity.Content;
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
public interface IContentService extends IService<Content> {

    /**
     * 提交内容
     *
     * @param content
     * @return
     */
    boolean postContent(Content content);

    /**
     * 删除内容，但不是真删
     *
     * @param content
     * @param token
     * @return
     */
    boolean deleteContent(Content content, String token);

    /**
     * 通过contentId来获得内容链
     *
     * @param contentId
     * @return
     */
    IPage<Content> getContentLink(Page<Content> page, Long contentId);

    /**
     * 通过contentId来获得内容链(显示层需要的VO)
     *
     * @param page
     * @param contentId
     * @return
     */
    IPage<ContentVO> getContentVOLink(Page<Content> page, Long contentId);

    /**
     * 通过contentIdList获得contentVOList
     *
     * @param contentIdList
     * @return
     */
    List<ContentVO> getContentVOList(List<Long> contentIdList);

    /**
     * 先获得根路径，再去获得全部内容链。
     *
     * @param contentId
     * @return
     */
    List<Long> getContentParentPaths(Long contentId);


    /**
     * 获得内容列表的评论数map
     *
     * @param contentIdList
     * @return
     */
    Map<Long, Integer> getContentCommentNumsMap(List<Long> contentIdList);

    /**
     * 获得内容的引用数量
     *
     * @param contentIdList
     * @return
     */
    Map<Long, Integer> getContentQuoteNumsMap(List<Long> contentIdList);

    /**
     * 获得引用了该条内容的用户
     *
     * @param page
     * @param contentId
     * @return
     */
    IPage<String> getContentQuoteUsernamePage(Page<String> page, Long contentId);
}
