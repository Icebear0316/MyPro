package cn.tedu.tea.admin.server.content.service.impl;

import cn.tedu.tea.admin.server.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.param.CategoryAddNewParam;
import cn.tedu.tea.admin.server.content.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理类别数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public CategoryServiceImpl() {
        log.info("创建业务对象：CategoryServiceImpl");
    }

    @Override
    public void addNew(CategoryAddNewParam categoryAddNewParam) {
        log.debug("开始处理【新增类别类别】的业务，参数：{}", categoryAddNewParam);

        Category category = new Category();
        BeanUtils.copyProperties(categoryAddNewParam, category);
        categoryRepository.insert(category);
    }

}