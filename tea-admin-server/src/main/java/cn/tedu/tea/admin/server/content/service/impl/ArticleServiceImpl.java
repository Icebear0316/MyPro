package cn.tedu.tea.admin.server.content.service.impl;

import cn.tedu.tea.admin.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import cn.tedu.tea.admin.server.content.pojo.param.ArticleAddNewParam;
import cn.tedu.tea.admin.server.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理文章数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleRepository articleRepository;

    public ArticleServiceImpl() {
        log.info("创建业务对象：ArticleServiceImpl");
    }

    @Override
    public void addNew(ArticleAddNewParam articleAddNewParam) {
        log.debug("开始处理【新增文章文章】的业务，参数：{}", articleAddNewParam);

        Article article = new Article();
        BeanUtils.copyProperties(articleAddNewParam, article);
        articleRepository.insert(article);
    }

}