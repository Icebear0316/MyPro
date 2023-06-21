package cn.tedu.tea.admin.server.account.service.impl;

import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void login(UserLoginInfoParam userLoginInfoParam) {
        log.debug("开始处理【用户登录】的业务，参数：{}", userLoginInfoParam);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
               userLoginInfoParam.getUsername(), userLoginInfoParam.getPassword());
        log.debug("准备调用AuthenticationManager的认证方法，判断此用户名、密码是否可以成功登录……");
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);

        log.debug("验证用户登录成功，准备将认证信息结果存入到SecurityContext中");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticateResult);
        log.debug("已经将认证信息存入到SecurityContext中，登录业务处理完成！");
    }

}
