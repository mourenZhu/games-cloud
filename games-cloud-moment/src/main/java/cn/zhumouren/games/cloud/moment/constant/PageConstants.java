package cn.zhumouren.games.cloud.moment.constant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description todo
 * @Author mourenZhu
 * @Date 2021/1/28 17:19
 * @Version 1.0
 **/
@Component
public class PageConstants {

    private static Integer current;

    private static Integer size;

    @Value("${constant.page.current}")
    public static void setCurrent(Integer current) {
        PageConstants.current = current;
    }

    @Value("${constant.page.size}")
    public static void setSize(Integer size) {
        PageConstants.size = size;
    }

    public static Page constantPageConfig(Page page, Integer current, Integer size) {
        if (current == null || size == null) {
            page.setCurrent(PageConstants.current);
            page.setSize(PageConstants.size);
        } else {
            page.setCurrent(current);
            page.setSize(size);
        }
        return page;
    }
}
