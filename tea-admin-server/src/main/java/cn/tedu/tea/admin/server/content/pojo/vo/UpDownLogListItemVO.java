package cn.tedu.tea.admin.server.content.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表项VO类：内容-顶踩日志
 *
 */
@Data
public class UpDownLogListItemVO implements Serializable {

    /**
     * 数据ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 资源类型，0=文章，1=评论
     */
    private Integer resourceType;
    /**
     * 资源ID
     */
    private Long resourceId;
    /**
     * 操作类型，0=踩，1=顶
     */
    private Integer opType;

}
