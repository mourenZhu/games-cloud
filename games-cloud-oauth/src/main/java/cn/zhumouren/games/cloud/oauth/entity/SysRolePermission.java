package cn.zhumouren.games.cloud.oauth.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 权限 ID
     */
    private Long permissionId;


}
