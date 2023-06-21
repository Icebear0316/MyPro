package cn.tedu.tea.admin.server.account.service;

import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;

public interface IUserService {

    /**
     * 用户登录
     *
     * @param userLoginInfoParam 封装了登录信息的对象
     */
    void login(UserLoginInfoParam userLoginInfoParam);

}
