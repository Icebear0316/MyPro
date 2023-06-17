package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;

/**
 * 处理标签数据的业务接口
 *
 */
public interface ITagService {

    /**
     * 新增标签类型
     *
     * @param tagTypeAddNewParam 新增的标签类型数据
     */
    void addNew(TagTypeAddNewParam tagTypeAddNewParam);

    // 新增标签
    // void addNew(TagAddNewParam tagAddNewParam);
    // TagAddNewParam: name, parent_id, enable, sort

}
