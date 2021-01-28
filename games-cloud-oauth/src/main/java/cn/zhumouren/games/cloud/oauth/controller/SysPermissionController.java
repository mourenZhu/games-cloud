package cn.zhumouren.games.cloud.oauth.controller;


import cn.zhumouren.games.cloud.oauth.entity.SysPermission;
import cn.zhumouren.games.cloud.oauth.service.ISysPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-14
 */
@RestController
@RequestMapping("/permission")
public class SysPermissionController {

    @Autowired
    private ISysPermissionService permissionService;

    @GetMapping("/is")
    public String getPermissionByPath(@RequestParam("path") String path) {
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", path);
        if (permissionService.getOne(queryWrapper) != null) {
            return permissionService.getOne(queryWrapper).getEnname();
        }
        return "";
    }
}
