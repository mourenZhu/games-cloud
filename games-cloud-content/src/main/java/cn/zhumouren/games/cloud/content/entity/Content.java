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
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 父节点username
     */
    private String parentUsername;

    /**
     * 根节点路径
     */
    private String parentPaths;

    /**
     * 引用内容id
     */
    private Long quoteId;

    /**
     * 作者用户
     */
    private String username;

    /**
     * 链接
     */
    private String url;

    /**
     * 图片绝对路径
     */
    private String pics;

    /**
     * 内容
     */
    private String content;

    private LocalDateTime created;

    private LocalDateTime updated;

    /**
     * 是否删除
     */
    private Boolean isDelete;


}
