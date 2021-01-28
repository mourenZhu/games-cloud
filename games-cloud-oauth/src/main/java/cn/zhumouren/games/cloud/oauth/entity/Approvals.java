package cn.zhumouren.games.cloud.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_approvals")
public class Approvals implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private String userid;

    @TableField("clientId")
    private String clientid;

    private String scope;

    private String status;

    @TableField("expiresAt")
    private LocalDateTime expiresat;

    @TableField("lastModifiedAt")
    private LocalDateTime lastmodifiedat;


}
