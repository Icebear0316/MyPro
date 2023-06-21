package cn.tedu.tea.admin.server.account.security;

import lombok.extern.slf4j.Slf4j;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // root / 123456
        if ("root".equals(s)) {
            UserDetails userDetails = User.builder()
                    .username("root")
                    .password("$2a$10$N.ZOn9G6/YLFixAOPMg/h.z7pCu6v2XyFDtC4q.jeeGm/TEZyj15C") // 此密文的原文是：123456
                    .disabled(false) // 账号是否被禁用
                    .accountLocked(false) // 账号是否被锁定，当前项目中无此概念，则所有账号的此属性都是false
                    .accountExpired(false) // 账号是否过期，当前项目中无此概念，则所有账号的此属性都是false
                    .credentialsExpired(false) // 凭证是否过期，当前项目中无此概念，则所有账号的此属性都是false
                    .authorities("山寨权限")
                    .build();
            return userDetails;
        } else {
            return null;
        }
    }

}
