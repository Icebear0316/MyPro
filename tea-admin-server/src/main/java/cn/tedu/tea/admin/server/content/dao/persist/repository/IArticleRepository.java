package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理文章数据的存储库接口
 *
 */
@Transactional
public interface IArticleRepository {

    /**
     * 插入文章数据
     *
     * @param article 文章数据
     * @return 受影响的行数
     */

    int insert(Article article);

}
