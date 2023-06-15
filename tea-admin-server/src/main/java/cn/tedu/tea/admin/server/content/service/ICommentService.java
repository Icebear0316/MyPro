package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.CommentAddNewParam;

/**
 * 处理评论数据的业务接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface ICommentService {

    /**
     * 新增评论
     *
     * @param commentAddNewParam 新增的评论数据
     */
    void addNew(CommentAddNewParam commentAddNewParam);

}
