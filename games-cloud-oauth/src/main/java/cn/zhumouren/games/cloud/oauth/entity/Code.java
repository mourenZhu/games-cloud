package cn.zhumouren.games.cloud.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Blob;
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
@TableName("oauth_code")
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private Blob authentication;


}
