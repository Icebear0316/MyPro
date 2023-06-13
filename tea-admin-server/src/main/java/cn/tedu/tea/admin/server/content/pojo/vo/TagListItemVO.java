package cn.tedu.tea.admin.server.content.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表项VO类：内容-标签
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public class TagListItemVO implements Serializable {

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

}
