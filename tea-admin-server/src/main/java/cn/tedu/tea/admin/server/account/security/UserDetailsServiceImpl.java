package cn.tedu.tea.admin.server.account.security;

import cn.tedu.tea.admin.server.account.dao.persist.repository.IUserRepository;
import cn.tedu.tea.admin.server.account.pojo.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security框架自动调用了UserDetailsService对象，将根据用户名获取用户详情，参数：{}", s);
        UserLoginInfoVO loginInfo = userRepository.getLoginInfoByUsername(s);
        log.debug("根据用户名【{}】从数据库中查询用户详情，查询结果：{}", s, loginInfo);

        if (loginInfo == null) {
            return null;
        }

        UserDetails userDetails = User.builder()
                .username(loginInfo.getUsername())
                .password(loginInfo.getPassword()) // 密文
                .disabled(loginInfo.getEnable() == 0) // 账号是否被禁用
                .accountLocked(false) // 账号是否被锁定，当前项目中无此概念，则所有账号的此属性都是false
                .accountExpired(false) // 账号是否过期，当前项目中无此概念，则所有账号的此属性都是false
                .credentialsExpired(false) // 凭证是否过期，当前项目中无此概念，则所有账号的此属性都是false
                .authorities("山寨权限")
                .build();
        log.debug("即将向Spring Security框架返回UserDetails类型的结果：{}", userDetails);
        log.debug("接下来，将由Spring Security框架自动验证用户状态、密码等，以判断是否可以成功登录！");
        return userDetails;
    }

}
