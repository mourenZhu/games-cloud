package cn.zhumouren.games.cloud.oauth.service.impl;

import cn.zhumouren.games.cloud.oauth.entity.RefreshToken;
import cn.zhumouren.games.cloud.oauth.mapper.RefreshTokenMapper;
import cn.zhumouren.games.cloud.oauth.service.IRefreshTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-14
 */
@Service
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshToken> implements IRefreshTokenService {

}
