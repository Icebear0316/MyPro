package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.CommentAddNewParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理评论数据的业务接口
 *
 */
@Transactional
public interface ICommentService {

    /**
     * 新增评论
     *
     * @param commentAddNewParam 新增的评论数据
     */
    void addNew(CommentAddNewParam commentAddNewParam);

}
