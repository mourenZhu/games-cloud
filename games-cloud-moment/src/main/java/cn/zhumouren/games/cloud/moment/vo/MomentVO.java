package cn.zhumouren.games.cloud.moment.vo;

import cn.zhumouren.games.cloud.moment.entity.Moment;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 展示在前端的对象
 * @Author mourenZhu
 * @Date 2021/1/24 14:03
 * @Version 1.0
 **/
@Data
public class MomentVO implements Serializable {

    private Moment moment;

    private Integer likeNums;

    private Integer commentNums;

    private Integer forwardNums;

    private Integer quoteNums;
}
