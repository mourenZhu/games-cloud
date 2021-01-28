package cn.zhumouren.games.cloud.oauth.service.impl;

import cn.zhumouren.games.cloud.oauth.entity.AccessToken;
import cn.zhumouren.games.cloud.oauth.mapper.AccessTokenMapper;
import cn.zhumouren.games.cloud.oauth.service.IAccessTokenService;
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
public class AccessTokenServiceImpl extends ServiceImpl<AccessTokenMapper, AccessToken> implements IAccessTokenService {

}
