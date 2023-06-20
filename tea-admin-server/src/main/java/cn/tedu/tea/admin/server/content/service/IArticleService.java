package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.ArticleAddNewParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理文章数据的业务接口
 *
 */
@Transactional
public interface IArticleService {

    /**
     * 新增文章
     *
     * @param articleAddNewParam 新增的文章数据
     */
    void addNew(ArticleAddNewParam articleAddNewParam);

}
