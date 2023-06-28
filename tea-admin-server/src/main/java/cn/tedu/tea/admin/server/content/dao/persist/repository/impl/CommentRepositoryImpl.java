package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.admin.server.content.dao.persist.mapper.CommentMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ICommentRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import cn.tedu.tea.admin.server.content.pojo.vo.CommentListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.CommentStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 处理评论数据的存储库实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class CommentRepositoryImpl implements ICommentRepository {

    @Autowired
    private CommentMapper commentMapper;

    public CommentRepositoryImpl() {
        log.info("创建存储库对象：CommentRepositoryImpl");
    }

    @Override
    public int insert(Comment comment) {
        log.debug("开始执行【插入评论】的数据访问，参数：{}", comment);
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("开始执行【根据ID删除评论】的数据访问，参数：{}", id);
        return commentMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("开始执行【批量删除评论】的数据访问，参数：{}", idList);
        return commentMapper.deleteBatchIds(idList);
    }

    @Override
    public int deleteByArticle(Long articleId) {
        log.debug("开始执行【根据文章删除评论数据】的数据访问，参数：{}", articleId);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        return commentMapper.delete(queryWrapper);
    }

    @Override
    public int update(Comment comment) {
        log.debug("开始执行【更新评论】的数据访问，参数：{}", comment);
        return commentMapper.updateById(comment);
    }

    @Override
    public int countByArticle(Long articleId) {
        log.debug("开始执行【根据文章统计评论表中的数据的数量】的数据访问，参数：{}", articleId);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        return commentMapper.selectCount(queryWrapper);
    }

    @Override
    public CommentStandardVO getStandardById(Long id) {
        log.debug("开始执行【根据ID查询评论信息】的数据访问，参数：{}", id);
        return commentMapper.getStandardById(id);
    }

    @Override
    public PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询评论列表】的数据访问，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.list();
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        PageData<CommentListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public PageData<CommentListItemVO> listByArticleId(Long articleId, Integer pageNum, Integer pageSize) {
        log.debug("开始执行【根据文章查询评论列表】的数据访问，文章：{}，页码：{}，每页记录数：{}", articleId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.listByArticleId(articleId);
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        PageData<CommentListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

}