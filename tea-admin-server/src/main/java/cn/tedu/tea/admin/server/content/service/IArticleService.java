package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.security.CurrentPrincipal;
import cn.tedu.tea.admin.server.content.pojo.param.ArticleAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.admin.server.content.service.impl.ArticleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理文章数据的业务接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */


@Transactional
public interface IArticleService {



    /**
     * 文章“是否启用”的状态文本
     */
    String[] CHECK_TEXT = {"不通过", "通过"};
    /**
     * 文章“是否显示”的状态文本
     */
    String[] DISPLAY_TEXT = {"隐藏", "显示"};
    /**
     * 踩或顶的显示文本
     */
    String[] UP_DOWN_TEXT = {"踩", "顶"};

    /**
     * 发布文章
     *
     * @param currentPrincipal   当事人
     * @param remoteAddr         IP地址
     * @param articleAddNewParam 新的文章数据
     */
    void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, ArticleAddNewParam articleAddNewParam);

    /**
     * 根据ID删除文章
     *
     * @param id 尝试删除的文章数据的ID
     */
    void delete(Long id);

    /**
     * 增加"顶"数量
     *
     * @param currentPrincipal 当事人
     * @param id               文章ID
     */
    void increaseUpCount(CurrentPrincipal currentPrincipal, Long id);

    /**
     * 增加"踩"数量
     *
     * @param currentPrincipal 当事人
     * @param id               文章ID
     */
    void increaseDownCount(CurrentPrincipal currentPrincipal, Long id);

    /**
     * 审核通过文章
     *
     * @param id 尝试审核通过的文章的ID
     */
    void passCheck(Long id);

    /**
     * 取消审核通过文章
     *
     * @param id 尝试取消审核的文章的ID
     */
    void cancelCheck(Long id);

    /**
     * 显示文章
     *
     * @param id 尝试显示的文章的ID
     */
    void setDisplay(Long id);

    /**
     * 隐藏（不显示）文章
     *
     * @param id 尝试隐藏的文章的ID
     */
    void setHidden(Long id);

    /**
     * 根据ID查询文章详情
     *
     * @param id 文章ID
     * @return 匹配的文章数据详情，如果没有匹配的数据，则返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查询文章列表，将使用默认的每页记录数
     *
     * @param pageNum 页码
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum);

    /**
     * 查询文章列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根据文章类别查询文章列表，将使用默认的每页记录数
     *
     * @param categoryId 文章类别的ID
     * @param pageNum    页码
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum);

    /**
     * 根据文章类别查询文章列表
     *
     * @param categoryId 文章类别的ID
     * @param pageNum    页码
     * @param pageSize   每页记录数
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize);

}
