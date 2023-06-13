package cn.tedu.tea.admin.server.content.pojo.entity;

public class Category {
    private Long id;
    private String name;
    private Long parent_id;
    private depth        TINYINT UNSIGNED DEFAULT 0 COMMENT '深度，最顶级类别的深度为1，次级为2，以此类推',
    keywords     VARCHAR(256) DEFAULT '' COMMENT '关键词列表，各关键词使用英文的逗号分隔',
    sort         TINYINT UNSIGNED DEFAULT 0 NOT NULL COMMENT '排序序号',
    icon         VARCHAR(256) DEFAULT '' COMMENT '图标图片的URL',
    enable       TINYINT UNSIGNED DEFAULT 0 COMMENT '是否启用，1=启用，0=未启用',
    is_parent    TINYINT UNSIGNED DEFAULT 0 COMMENT '是否为父级（是否包含子级），1=是父级，0=不是父级',
    is_display   TINYINT UNSIGNED DEFAULT 0 COMMENT '是否显示在导航栏中，1=启用，0=未启用',
    gmt_create   DATETIME     DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified
}
