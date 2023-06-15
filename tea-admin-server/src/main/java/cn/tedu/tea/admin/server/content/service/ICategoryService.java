package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.CategoryAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;

/**
 * 处理类别数据的业务接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface ICategoryService {

    /**
     * 新增类别
     *
     * @param categoryAddNewParam 新增的类别数据
     */
    void addNew(CategoryAddNewParam categoryAddNewParam);

}
