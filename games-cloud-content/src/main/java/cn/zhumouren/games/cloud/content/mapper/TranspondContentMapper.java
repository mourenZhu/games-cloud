package cn.zhumouren.games.cloud.content.mapper;

import cn.zhumouren.games.cloud.content.entity.TranspondContent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
@Repository
public interface TranspondContentMapper extends BaseMapper<TranspondContent> {

    /**
     * 获得内容转发数map
     *
     * @param contentIdList
     * @return
     */
    Map<Long, Integer> getContentTranspondNumsMap(@Param("contentIdList") List<Long> contentIdList);

}
