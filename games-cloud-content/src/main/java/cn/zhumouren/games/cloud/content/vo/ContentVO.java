package cn.zhumouren.games.cloud.content.vo;

import cn.zhumouren.games.cloud.content.entity.Content;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 展示在前端的对象
 * @Author mourenZhu
 * @Date 2021/1/24 14:03
 * @Version 1.0
 **/
@Data
public class ContentVO implements Serializable {

    private Content content;

    private Integer likeNums;

    private Integer commentNums;

    private Integer transpondNums;

    private Integer quoteNums;
}
