package cn.zhumouren.games.cloud.moment.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author mourenZhu
 * @Date 2021/1/25 23:03
 * @Version 1.0
 **/
public class ListUtils {

    /**
     * 把一段字符串转化为list
     * @param s
     * @return
     */
    public static List<Long> getListByString(String s) {
        List<Long> list = new ArrayList();
        s = s.replace("[", "").replace("]", "");
        while (true) {
            list.add(Long.valueOf(s.substring(0, s.indexOf(","))));
            s = s.substring(s.indexOf(",") + 1);
            if (s.indexOf(",") == -1) {
                list.add(Long.valueOf(s));
                return list;
            }
        }
    }

}
