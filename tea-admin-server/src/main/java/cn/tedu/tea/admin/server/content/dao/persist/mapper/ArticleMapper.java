package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理文章数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 1.0
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

    /**
     * 查询文章数据列表
     *
     * @return 文章数据列表
     */
    List<ArticleListItemVO> list();

    /**
     * 根据文章类别（parentId）查询其文章列表
     *
     * @param categoryId 文章类别的ID（parentId）
     * @return 文章列表
     */
    List<ArticleListItemVO> listByCategoryId(Long categoryId);

}