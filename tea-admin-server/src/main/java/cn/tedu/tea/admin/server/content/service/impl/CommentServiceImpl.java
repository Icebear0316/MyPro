package cn.tedu.tea.admin.server.content.service.impl;

import cn.tedu.tea.admin.server.content.dao.persist.repository.ICommentRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import cn.tedu.tea.admin.server.content.pojo.param.CommentAddNewParam;
import cn.tedu.tea.admin.server.content.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理评论数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    public CommentServiceImpl() {
        log.info("创建业务对象：CommentServiceImpl");
    }

    @Override
    public void addNew(CommentAddNewParam commentAddNewParam) {
        log.debug("开始处理【新增评论评论】的业务，参数：{}", commentAddNewParam);

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddNewParam, comment);
        commentRepository.insert(comment);
    }

}