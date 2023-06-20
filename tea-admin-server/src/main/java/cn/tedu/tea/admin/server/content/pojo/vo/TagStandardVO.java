package cn.tedu.tea.admin.server.content.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 标准VO类：内容-标签
 *
 */
@Data
public class TagStandardVO implements Serializable {

    /**
     * 数据ID
     */
    private Long id;
    /**
     * 标签名称
     */
    private String name;
    /**
     * 标签类别ID
     */
    private Long typeId;
    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;
    /**
     * 排序序号
     */
    private Integer sort;

}