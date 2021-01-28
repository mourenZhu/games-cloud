package cn.zhumouren.games.cloud.content.mapper;

import cn.zhumouren.games.cloud.content.entity.Content;
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
public interface ContentMapper extends BaseMapper<Content> {

    /**
     * 通过contentId来获得内容链
     *
     * @param contentId
     * @return
     */
    IPage<Content> getContentLink(Page<?> page, @Param("contentId") Long contentId, @Param("parentList") List<Long> parentPathsList);

    /**
     * 通过contentIdList获得contentList
     * @param contentIdList
     * @return
     */
    List<Content> getContentList(@Param("contentIdList") List<Long> contentIdList);

    /**
     * 获得内容列表的评论数map
     *
     * @param contentIdList
     * @return
     */
    Map<Long, Integer> getContentCommentMap(@Param("contentIdList") List<Long> contentIdList);

    /**
     * 获得内容列表的引用数map
     *
     * @param contentIdList
     * @return
     */
    Map<Long, Integer> getContentQuoteMap(@Param("contentIdList") List<Long> contentIdList);

    /**
     * 获得引用了该内容的用户
     *
     * @param page
     * @param contentId
     * @return
     */
    IPage<String> getContentQuoteUsernamePage(Page<String> page, @Param("contentId") Long contentId);

}
