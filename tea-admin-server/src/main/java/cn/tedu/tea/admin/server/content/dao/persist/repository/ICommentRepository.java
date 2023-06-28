package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import cn.tedu.tea.admin.server.content.pojo.vo.CommentListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.CommentStandardVO;

import java.util.Collection;

/**
 * 处理评论数据的存储库接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface ICommentRepository {

    /**
     * 插入评论数据
     *
     * @param comment 评论数据
     * @return 受影响的行数
     */
    int insert(Comment comment);

    /**
     * 根据id删除评论数据
     *
     * @param id 评论ID
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据若干个ID批量删除评论数据
     *
     * @param idList 若干个评论ID的数组
     * @return 受影响的行数
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根据文章删除评论数据
     *
     * @param articleId 文章ID
     * @return 受影响的行数
     */
    int deleteByArticle(Long articleId);

    /**
     * 根据id修改评论数据
     *
     * @param comment 封装了评论ID和新数据的对象
     * @return 受影响的行数
     */
    int update(Comment comment);

    /**
     * 根据文章统计评论表中的数据的数量
     *
     * @param articleId 文章ID
     * @return 文章匹配的评论数据的数量
     */
    int countByArticle(Long articleId);

    /**
     * 根据id查询评论数据详情
     *
     * @param id 评论ID
     * @return 匹配的评论数据详情，如果没有匹配的数据，则返回null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查询评论数据列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 评论数据列表
     */
    PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根据文章查询评论列表
     *
     * @param articleId 文章ID
     * @param pageNum   页码
     * @param pageSize  每页记录数
     * @return 评论列表
     */
    PageData<CommentListItemVO> listByArticleId(Long articleId, Integer pageNum, Integer pageSize);

}
