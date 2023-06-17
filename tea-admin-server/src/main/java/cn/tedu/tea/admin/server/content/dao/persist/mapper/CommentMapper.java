package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import cn.tedu.tea.admin.server.content.pojo.vo.CommentStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 处理评论数据的Mapper接口
 *
 *
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据ID查询评论
     *
     * @param id 评论ID
     * @return 匹配的评论，如果没有匹配的数据，则返回null
     */
    CommentStandardVO getStandardById(Long id);

}