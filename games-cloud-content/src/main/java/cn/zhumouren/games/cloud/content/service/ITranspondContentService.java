package cn.zhumouren.games.cloud.content.service;

import cn.zhumouren.games.cloud.content.entity.TranspondContent;
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
public interface ITranspondContentService extends IService<TranspondContent> {

    /**
     * 获得内容转发数map
     *
     * @param contentIdList
     * @return
     */
    Map<Long, Integer> getContentTranspondNumsMap(List<Long> contentIdList);

}
