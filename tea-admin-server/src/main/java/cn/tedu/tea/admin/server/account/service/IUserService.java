package cn.tedu.tea.admin.server.account.service;

import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.pojo.vo.UserListItemVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserLoginResultVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理用户数据的业务接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Transactional
public interface IUserService {

    /**
     * 启用状态的显示文本
     */
    String ENABLE_TEXT[] = {"禁用", "启用"};

    /**
     * 用户登录
     *
     * @param userLoginInfoParam 封装了登录信息的对象
     * @return 成功登录的用户的信息，包括：ID、用户名、头像、JWT等数据
     */
    UserLoginResultVO login(UserLoginInfoParam userLoginInfoParam);

    /**
     * 添加用户
     *
     * @param userAddNewParam 用户数据
     */
    void addNew(UserAddNewParam userAddNewParam);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void delete(Long id);

    /**
     * 启用用户
     *
     * @param id 用户ID
     */
    void setEnable(Long id);

    /**
     * 禁用用户
     *
     * @param id 用户ID
     */
    void setDisable(Long id);

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 匹配的用户信息
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 查询用户列表，将使用默认的每页记录数
     *
     * @param pageNum 页码
     * @return 用户列表
     */
    PageData<UserListItemVO> list(Integer pageNum);

    /**
     * 查询用户列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 用户列表
     */
    PageData<UserListItemVO> list(Integer pageNum, Integer pageSize);

}
