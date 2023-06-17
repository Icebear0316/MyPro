package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.admin.server.content.dao.persist.mapper.TagMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ITagRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public PageData<TagTypeListItemVO> listTagType(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询标签类别列表】，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<TagTypeListItemVO> tagTypeList = tagMapper.listTagType();
        PageInfo<TagTypeListItemVO> pageInfo = new PageInfo<>(tagTypeList);
        PageData<TagTypeListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

}