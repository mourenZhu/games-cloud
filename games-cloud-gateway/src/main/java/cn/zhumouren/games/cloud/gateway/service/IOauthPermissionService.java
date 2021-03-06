package cn.zhumouren.games.cloud.gateway.service;

import cn.zhumouren.games.cloud.gateway.service.fallback.OauthPermissionServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "server-oauth2", fallback = OauthPermissionServiceFallback.class)
public interface IOauthPermissionService {

    @GetMapping("/permission/is")
    String getPermissionByPath(@RequestParam("path") String path);
}
