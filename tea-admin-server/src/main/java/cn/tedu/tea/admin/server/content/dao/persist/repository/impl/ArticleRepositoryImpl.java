package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.ArticleMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        log.debug("开始向【文章表】中插入数据：{}", article);
        return articleMapper.insert(article);
    }

}