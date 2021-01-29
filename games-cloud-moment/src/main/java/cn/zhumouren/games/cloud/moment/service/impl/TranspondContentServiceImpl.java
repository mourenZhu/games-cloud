package cn.zhumouren.games.cloud.moment.service.impl;

import cn.zhumouren.games.cloud.moment.entity.TranspondContent;
import cn.zhumouren.games.cloud.moment.mapper.TranspondContentMapper;
import cn.zhumouren.games.cloud.moment.service.ITranspondContentService;
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
public class TranspondContentServiceImpl extends ServiceImpl<TranspondContentMapper, TranspondContent> implements ITranspondContentService {

    @Autowired
    private TranspondContentMapper transpondContentMapper;
    @Override
    public Map<Long, Integer> getContentTranspondNumsMap(List<Long> contentIdList) {
        return transpondContentMapper.getContentTranspondNumsMap(contentIdList);
    }
}
