package cn.tedu.tea.admin.server.content.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增标签类型的参数类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class TagTypeAddNewParam implements Serializable {

    /**
     * 标签类型名称
     */
    private String name;
    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;
    /**
     * 排序序号
     */
    private Integer sort;

}
