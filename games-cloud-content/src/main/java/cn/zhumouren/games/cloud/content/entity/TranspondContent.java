package cn.zhumouren.games.cloud.content.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
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
public class TranspondContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long contentId;

    /**
     * 引用者用户
     */
    private String username;

    private LocalDateTime created;


}
