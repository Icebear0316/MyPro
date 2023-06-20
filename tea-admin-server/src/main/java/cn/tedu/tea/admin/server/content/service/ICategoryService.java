package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.CategoryAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理类别数据的业务接口
 */
@Transactional
public interface ICategoryService {

    /**
     * 新增类别
     *
     * @param categoryAddNewParam 新增的类别数据
     */
    void addNew(CategoryAddNewParam categoryAddNewParam);

}
