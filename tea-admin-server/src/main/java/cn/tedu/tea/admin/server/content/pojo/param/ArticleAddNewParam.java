package cn.tedu.tea.admin.server.content.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 新增文章的参数类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class ArticleAddNewParam implements Serializable {

    /**
     * 作者ID
     */
    private Long authorId;
    /**
     * 作者名字
     */
    private String authorName;
    /**
     * 类别ID
     */
    private Long categoryId;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String brief;
    /**
     * 标签列表，实际存入JSON数据
     */
    @ApiModelProperty(value = "标签列表，实际存入JSON数据", example = "[{\"id\":10, \"name\":\"Spring\"},{\"id\":11, \"name\":\"Spring MVC\"}]")
    private String tags;
    /**
     * 排序序号
     */
    private Integer sort;
    /**
     * 封面图
     */
    private String coverUrl;
    /**
     * 详情
     */
    private String detail;

}
