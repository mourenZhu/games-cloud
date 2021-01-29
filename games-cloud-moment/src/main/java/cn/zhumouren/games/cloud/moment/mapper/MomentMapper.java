package cn.zhumouren.games.cloud.moment.mapper;

import cn.zhumouren.games.cloud.moment.entity.Moment;
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
public interface MomentMapper extends BaseMapper<Moment> {

    /**
     * 通过contentId来获得内容链
     *
     * @param momentId
     * @return
     */
    IPage<Moment> getMomentLinkPage(Page<?> page, @Param("momentId") Long momentId, @Param("parentList") List<Long> parentPathsList);

    /**
     * 通过momentIdList获得momentList
     * @param momentIdList
     * @return
     */
    List<Moment> listMoment(@Param("momentIdList") List<Long> momentIdList);

    /**
     * 获得内容列表的评论数map
     *
     * @param momentIdList
     * @return
     */
    Map<Long, Integer> getMomentCommentMap(@Param("momentIdList") List<Long> momentIdList);

    /**
     * 获得内容列表的引用数map
     *
     * @param momentIdList
     * @return
     */
    Map<Long, Integer> getMomentQuoteMap(@Param("momentIdList") List<Long> momentIdList);

    /**
     * 获得引用了该内容的用户
     *
     * @param page
     * @param momentId
     * @return
     */
    IPage<String> getMomentQuoteUsernamePage(Page<String> page, @Param("momentId") Long momentId);

}
