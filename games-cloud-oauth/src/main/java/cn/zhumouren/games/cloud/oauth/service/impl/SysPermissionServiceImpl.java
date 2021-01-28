package cn.zhumouren.games.cloud.oauth.service.impl;

import cn.zhumouren.games.cloud.oauth.entity.SysPermission;
import cn.zhumouren.games.cloud.oauth.mapper.SysPermissionMapper;
import cn.zhumouren.games.cloud.oauth.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-14
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;
    @Override
    public List<SysPermission> selectByUsername(String username) {
        return permissionMapper.selectByUsername(username);
    }
}
