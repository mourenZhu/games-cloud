package cn.zhumouren.games.cloud.gateway.service.fallback;

import cn.zhumouren.games.cloud.gateway.service.IOauthPermissionService;
import org.springframework.stereotype.Component;

@Component
public class OauthPermissionServiceFallback implements IOauthPermissionService {
    @Override
    public String getPermissionByPath(String path) {
        return "出现意外,请稍等！";
    }
}
