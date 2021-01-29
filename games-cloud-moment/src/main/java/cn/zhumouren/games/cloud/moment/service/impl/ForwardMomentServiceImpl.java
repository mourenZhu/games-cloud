package cn.zhumouren.games.cloud.moment.service.impl;

import cn.zhumouren.games.cloud.moment.entity.ForwardMoment;
import cn.zhumouren.games.cloud.moment.mapper.ForwardMomentMapper;
import cn.zhumouren.games.cloud.moment.service.IForwardMomentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
@Service
public class ForwardMomentServiceImpl extends ServiceImpl<ForwardMomentMapper, ForwardMoment> implements IForwardMomentService {

    @Autowired
    private ForwardMomentMapper forwardMomentMapper;
    @Override
    public Map<Long, Integer> getMomentForwardNumsMap(List<Long> momentIdList) {
        return forwardMomentMapper.getMomentForwardNumsMap(momentIdList);
    }
}
