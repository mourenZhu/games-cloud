package cn.zhumouren.games.cloud.moment.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

public class JwtUtils {

    public static String getString(String accessToken, String name){
        Jwt jwt = JwtHelper.decode(accessToken);
        JSONObject jsonObject = JSONObject.parseObject(jwt.getClaims());
        return jsonObject.getString(name);
    }

}
