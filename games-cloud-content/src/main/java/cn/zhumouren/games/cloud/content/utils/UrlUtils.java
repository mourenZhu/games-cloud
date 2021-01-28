package cn.zhumouren.games.cloud.content.utils;

public class UrlUtils {

    public final static String WebHost = "http://games.zhumouren.cn";
    public static String creatContentUrl(String username, Long contentId){
        String url = WebHost + "/" + username + "/status/" + contentId;
        return url;
    }
}
