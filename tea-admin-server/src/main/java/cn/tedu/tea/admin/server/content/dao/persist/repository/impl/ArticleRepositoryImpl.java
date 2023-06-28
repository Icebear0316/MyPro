package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.admin.server.content.dao.persist.mapper.ArticleMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 处理文章数据的存储库实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class ArticleRepositoryImpl implements IArticleRepository {

    @Autowired
    private ArticleMapper articleMapper;

    public ArticleRepositoryImpl() {
        log.info("创建存储库对象：ArticleRepositoryImpl");
    }

    @Override
    public int insert(Article article) {
        log.debug("开始执行【插入文章】的数据访问，参数：{}", article);
        return articleMapper.insert(article);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("开始执行【根据ID删除文章】的数据访问，参数：{}", id);
        return articleMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("开始执行【批量删除文章】的数据访问，参数：{}", idList);
        return articleMapper.deleteBatchIds(idList);
    }

    @Override
    public int update(Article article) {
        log.debug("开始执行【更新文章】的数据访问，参数：{}", article);
        return articleMapper.updateById(article);
    }

    @Override
    public int count() {
        log.debug("开始执行【统计文章的数量】的数据访问，参数：无");
        return articleMapper.selectCount(null);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("开始执行【根据ID查询文章信息】的数据访问，参数：{}", id);
        return articleMapper.getStandardById(id);
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询文章列表】的数据访问，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.list();
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        PageData<ArticleListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询文章列表】的数据访问，文章类别：{}，页码：{}，每页记录数：{}", categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByCategoryId(categoryId);
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        PageData<ArticleListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

}