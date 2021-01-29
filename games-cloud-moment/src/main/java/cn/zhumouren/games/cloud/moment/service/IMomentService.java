package cn.zhumouren.games.cloud.moment.service;

import cn.zhumouren.games.cloud.moment.entity.Moment;
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
public interface IMomentService extends IService<Moment> {

    /**
     * 提交内容
     *
     * @param moment
     * @return
     */
    boolean postMoment(Moment moment);

    /**
     * 删除内容，但不是真删
     *
     * @param moment
     * @param token
     * @return
     */
    boolean deleteMoment(Moment moment, String token);

    /**
     * 通过contentId来获得内容链
     *
     * @param momentId
     * @return
     */
    IPage<Moment> getMomentLinkPage(Page<Moment> page, Long momentId);

    /**
     * 通过contentId来获得内容链(显示层需要的VO)
     *
     * @param page
     * @param momentId
     * @return
     */
    IPage<MomentVO> getMomentVOLinkPage(Page<Moment> page, Long momentId);

    /**
     * 通过contentIdList获得contentVOList
     *
     * @param momentIdList
     * @return
     */
    List<MomentVO> listMomentVOs(List<Long> momentIdList);

    /**
     * 先获得根路径，再去获得全部内容链。
     *
     * @param momentId
     * @return
     */
    List<Long> listMomentParentPaths(Long momentId);


    /**
     * 获得内容列表的评论数map
     *
     * @param momentIdList
     * @return
     */
    Map<Long, Integer> getMomentCommentNumsMap(List<Long> momentIdList);

    /**
     * 获得内容的引用数量
     *
     * @param momentIdList
     * @return
     */
    Map<Long, Integer> getMomentQuoteNumsMap(List<Long> momentIdList);

    /**
     * 获得引用了该条内容的用户
     *
     * @param page
     * @param momentId
     * @return
     */
    IPage<String> getMomentQuoteUsernamePage(Page<String> page, Long momentId);
}
