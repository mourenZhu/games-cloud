package cn.zhumouren.games.cloud.moment.service;

import cn.zhumouren.games.cloud.moment.entity.ForwardMoment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
public interface IForwardMomentService extends IService<ForwardMoment> {

    /**
     * 获得内容转发数map
     *
     * @param momentIdList
     * @return
     */
    Map<Long, Integer> getMomentForwardNumsMap(List<Long> momentIdList);

}
