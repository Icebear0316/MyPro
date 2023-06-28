package cn.tedu.tea.admin.server.content.service.impl;

import cn.tedu.tea.admin.server.common.consts.ContentConsts;
import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.security.CurrentPrincipal;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IArticleDetailRepository;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ICommentRepository;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IUpDownLogRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import cn.tedu.tea.admin.server.content.pojo.entity.ArticleDetail;
import cn.tedu.tea.admin.server.content.pojo.entity.UpDownLog;
import cn.tedu.tea.admin.server.content.pojo.param.ArticleAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.admin.server.content.pojo.vo.UpDownLogStandardVO;
import cn.tedu.tea.admin.server.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 处理文章数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class ArticleServiceImpl implements IArticleService, ContentConsts {

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IArticleRepository articleRepository;
    @Autowired
    private IArticleDetailRepository articleDetailRepository;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IUpDownLogRepository upDownLogRepository;

    public ArticleServiceImpl() {
        log.debug("创建业务类对象：ArticleServiceImpl");
    }

    @Override
    public void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, ArticleAddNewParam articleAddNewParam) {
        log.debug("开始处理【发布文章】的业务，当事人：{}，IP地址：{}，参数：{}", currentPrincipal, remoteAddr, articleAddNewParam);

        Article article = new Article();
        BeanUtils.copyProperties(articleAddNewParam, article);
        article.setAuthorId(currentPrincipal.getId());
        article.setAuthorName(currentPrincipal.getUsername());
        article.setIp(remoteAddr);
        article.setClickCount(0);
        article.setUpCount(0);
        article.setDownCount(0);
        article.setCheckState(0);
        article.setDisplayState(0);
        int rows = articleRepository.insert(article);
        if (rows != 1) {
            String message = "发布文章失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setArticleId(article.getId());
        articleDetail.setDetail(articleAddNewParam.getDetail());
        rows = articleDetailRepository.insert(articleDetail);
        if (rows != 1) {
            String message = "发布文章失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【根据ID删除文章】的业务，参数：{}", id);
        ArticleStandardVO queryResult = articleRepository.getStandardById(id);
        if (queryResult == null) {
            // 是：数据不存在，抛出异常
            String message = "删除文章失败，尝试删除的文章数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int rows = articleRepository.deleteById(id);
        if (rows != 1) {
            String message = "删除文章失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        rows = articleDetailRepository.deleteByArticle(id);
        if (rows != 1) {
            String message = "删除文章失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        rows = commentRepository.deleteByArticle(id);
        if (rows < 0) {
            String message = "删除文章失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        rows = upDownLogRepository.deleteByUserAndResource(queryResult.getAuthorId(), RESOURCE_TYPE_ARTICLE, id);
        if (rows < 0) {
            String message = "删除文章失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }
    }

    @Override
    public void increaseUpCount(CurrentPrincipal currentPrincipal, Long id) {
        log.debug("开始处理【增加文章的\"顶\"数量】的业务，当事人：{}, 文章：{}", currentPrincipal, id);
        increaseUpOrDownCount(currentPrincipal.getId(), id, OP_TYPE_UP);
    }

    @Override
    public void increaseDownCount(CurrentPrincipal currentPrincipal, Long id) {
        log.debug("开始处理【增加文章的\"踩\"数量】的业务，当事人：{}, 文章：{}", currentPrincipal, id);
        increaseUpOrDownCount(currentPrincipal.getId(), id, OP_TYPE_DOWN);
    }

    @Override
    public void passCheck(Long id) {
        log.debug("开始处理【审核通过文章】的业务，参数：{}", id);
        updateCheckById(id, 1);
    }

    @Override
    public void cancelCheck(Long id) {
        log.debug("开始处理【取消审核通过文章】的业务，参数：{}", id);
        updateCheckById(id, 0);
    }

    @Override
    public void setDisplay(Long id) {
        log.debug("开始处理【显示文章】的业务，参数：{}", id);
        updateDisplayById(id, 1);
    }

    @Override
    public void setHidden(Long id) {
        log.debug("开始处理【不显示文章】的业务，参数：{}", id);
        updateDisplayById(id, 0);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("开始处理【根据ID查询文章详情】的业务，参数：{}", id);
        ArticleStandardVO queryResult = articleRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "查询文章详情失败，文章数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum) {
        log.debug("开始处理【查询文章列表】的业务，页码：{}", pageNum);
        PageData<ArticleListItemVO> pageData = articleRepository.list(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【查询文章列表】的业务，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageData<ArticleListItemVO> pageData = articleRepository.list(pageNum, pageSize);
        return pageData;
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum) {
        log.debug("开始处理【根据文章类别查询文章列表】的业务，文章类别：{}, 页码：{}", categoryId, pageNum);
        PageData<ArticleListItemVO> pageData = articleRepository.listByCategoryId(categoryId, pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("开始处理【根据文章类别查询文章列表】的业务，文章类别：{}, 页码：{}，每页记录数：{}", categoryId, pageNum, pageSize);
        PageData<ArticleListItemVO> pageData = articleRepository.listByCategoryId(categoryId, pageNum, pageSize);
        return pageData;
    }

    private void updateCheckById(Long id, Integer checkState) {
        ArticleStandardVO currentArticle = articleRepository.getStandardById(id);
        if (currentArticle == null) {
            String message = "将文章的审核状态修改为【" + CHECK_TEXT[checkState] + "】失败，文章数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentArticle.getCheckState() == checkState) {
            String message = "将文章的审核状态修改为【" + CHECK_TEXT[checkState] + "】失败，此文章已经处于" + CHECK_TEXT[checkState] + "状态！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Article updateArticle = new Article();
        updateArticle.setId(id);
        updateArticle.setCheckState(checkState);
        int rows = articleRepository.update(updateArticle);
        if (rows != 1) {
            String message = "将文章的审核状态修改为【" + CHECK_TEXT[checkState] + "】失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void updateDisplayById(Long id, Integer displayState) {
        ArticleStandardVO currentArticle = articleRepository.getStandardById(id);
        if (currentArticle == null) {
            String message = "将文章的显示状态修改为【" + DISPLAY_TEXT[displayState] + "】失败，文章数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentArticle.getDisplayState() == displayState) {
            String message = "将文章的显示状态修改为【" + DISPLAY_TEXT[displayState] + "】失败，此文章已经处于" + DISPLAY_TEXT[displayState] + "状态！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Article updateArticle = new Article();
        updateArticle.setId(id);
        updateArticle.setDisplayState(displayState);
        int rows = articleRepository.update(updateArticle);
        if (rows != 1) {
            String message = "将文章的显示状态修改为【" + DISPLAY_TEXT[displayState] + "】失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void increaseUpOrDownCount(Long userId, Long articleId, Integer opType) {
        ArticleStandardVO currentArticle = articleRepository.getStandardById(articleId);
        if (currentArticle == null) {
            String message = "操作失败，文章数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        UpDownLogStandardVO queryResult = upDownLogRepository.getByUserAndResource(userId, RESOURCE_TYPE_ARTICLE, articleId);

        if (queryResult != null) {
            if (queryResult.getOpType() == opType) {
                log.debug("当前用户已经【{}】过此文章，将不执行任何操作，直接结束！", UP_DOWN_TEXT[opType]);
                String message = "操作失败，您此前已经" + UP_DOWN_TEXT[opType] + "过此文章！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            } else {
                log.debug("当前用户此前【{}】过此文章，将删除此前的记录！", UP_DOWN_TEXT[(opType + 1) % 2]);
                int rows = upDownLogRepository.deleteByUserAndResource(userId, RESOURCE_TYPE_ARTICLE, articleId);
                if (rows != 1) {
                    String message = "操作失败，服务器忙，请稍后再尝试！";
                    log.warn(message);
                    throw new ServiceException(ServiceCode.ERROR_DELETE, message);
                }
                Article article = new Article();
                article.setId(articleId);
                if (OP_TYPE_UP == opType) {
                    article.setDownCount(currentArticle.getDownCount() - 1);
                } else {
                    article.setUpCount(currentArticle.getUpCount() - 1);
                }
                rows = articleRepository.update(article);
                if (rows != 1) {
                    String message = "操作失败，服务器忙，请稍后再尝试！";
                    log.warn(message);
                    throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
                }
            }
        }

        UpDownLog upDownLog = new UpDownLog();
        upDownLog.setUserId(userId);
        upDownLog.setResourceType(RESOURCE_TYPE_ARTICLE);
        upDownLog.setResourceId(articleId);
        upDownLog.setOpType(opType);
        int rows = upDownLogRepository.insert(upDownLog);
        if (rows != 1) {
            String message = "操作失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        Article article = new Article();
        article.setId(articleId);
        if (OP_TYPE_UP == opType) {
            article.setUpCount(currentArticle.getUpCount() + 1);
        } else {
            article.setDownCount(currentArticle.getDownCount() + 1);
        }
        rows = articleRepository.update(article);
        if (rows != 1) {
            String message = "操作失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

}