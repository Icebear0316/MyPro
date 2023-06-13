package cn.tedu.tea.admin.server.content.pojo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * 实体类：内容-标签
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public class Tag {
    /**
     * 数据ID
     */
    private Long id;
    /**
     * 标签名称
     */
    private String name;
    /**
     * 父级ID，为0的是标签分类，不为0的是标签
     */
    private Long parentId;
    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;
    /**
     * 排序序号
     */
    private Integer sort;
    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;
}
