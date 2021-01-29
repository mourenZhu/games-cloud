package cn.zhumouren.games.cloud.moment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("content_likes")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节点id
     */
    private Long contentId;

    /**
     * 用户
     */
    private String username;

    private LocalDateTime created;


}
