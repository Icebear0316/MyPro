package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.CategoryMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 处理类别数据的存储库实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryRepositoryImpl() {
        log.info("创建存储库对象：CategoryRepositoryImpl");
    }

    @Override
    public int insert(Category category) {
        log.debug("开始向【类别表】中插入数据：{}", category);
        return categoryMapper.insert(category);
    }

}