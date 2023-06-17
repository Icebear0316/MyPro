package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.ArticleAddNewParam;

/**
 * 处理文章数据的业务接口
 *
 */
public interface IArticleService {

    /**
     * 新增文章
     *
     * @param articleAddNewParam 新增的文章数据
     */
    void addNew(ArticleAddNewParam articleAddNewParam);

}
