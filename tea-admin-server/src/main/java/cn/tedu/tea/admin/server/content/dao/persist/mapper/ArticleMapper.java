package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 处理文章数据的Mapper接口
 *
 *
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据ID查询文章
     *
     * @param id 文章ID
     * @return 匹配的文章，如果没有匹配的数据，则返回null
     */
    ArticleStandardVO getStandardById(Long id);

}