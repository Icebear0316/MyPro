package cn.tedu.tea.admin.server.account.service.impl;

import cn.tedu.tea.admin.server.account.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public void login() {
        System.out.println("处理登录业务");
    }

}
