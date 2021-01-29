package cn.zhumouren.games.cloud.moment.mapper;

import cn.zhumouren.games.cloud.moment.entity.ForwardMoment;
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
public interface ForwardMomentMapper extends BaseMapper<ForwardMoment> {

    /**
     * 获得内容转发数map
     *
     * @param momentIdList
     * @return
     */
    Map<Long, Integer> getMomentForwardNumsMap(@Param("momentIdList") List<Long> momentIdList);

}
