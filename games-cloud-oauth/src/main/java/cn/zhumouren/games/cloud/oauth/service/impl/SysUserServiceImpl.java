package cn.zhumouren.games.cloud.oauth.service.impl;

import cn.zhumouren.games.cloud.oauth.entity.SysUser;
import cn.zhumouren.games.cloud.oauth.mapper.SysUserMapper;
import cn.zhumouren.games.cloud.oauth.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
