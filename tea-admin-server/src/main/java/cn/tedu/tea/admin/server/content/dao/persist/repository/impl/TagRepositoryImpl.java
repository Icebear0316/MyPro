package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.TagMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ITagRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 处理标签数据的存储库实现类
 *
 */
@Slf4j
@Repository
public class TagRepositoryImpl implements ITagRepository {

    @Autowired
    private TagMapper tagMapper;

    public TagRepositoryImpl() {
        log.info("创建存储库对象：TagRepositoryImpl");
    }

    @Override
    public int insert(Tag tag) {
        log.debug("开始向【标签表】中插入数据：{}", tag);
        return tagMapper.insert(tag);
    }

    @Override
    public int countByName(String name) {
        log.debug("根据名称【{}】统计【标签表】中的数据的数量", name);
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return tagMapper.selectCount(queryWrapper);
    }

}