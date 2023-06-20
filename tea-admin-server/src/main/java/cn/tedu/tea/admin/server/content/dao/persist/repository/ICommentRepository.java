package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理评论数据的存储库接口
 *
 */
@Transactional
public interface ICommentRepository {

    /**
     * 插入评论数据
     *
     * @param comment 评论数据
     * @return 受影响的行数
     */
    int insert(Comment comment);

}
