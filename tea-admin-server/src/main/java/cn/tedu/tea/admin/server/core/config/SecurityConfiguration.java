package cn.tedu.tea.admin.server.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security的配置类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 白名单
        String[] urls = {
                "/favicon.ico",
                "/doc.html",
                "/**/*.css",
                "/**/*.js",
                "/swagger-resources",
                "/v2/api-docs",
        };

        // 禁用“防止伪造的跨域攻击的防御机制”
        http.csrf().disable();

        // 配置请求授权
        // 如果某个请求被多次配置，按照“第一匹配原则”处理
        // 应该将精确的配置写在前面，将较模糊的配置写在后面
        http.authorizeRequests()
                .mvcMatchers(urls) // 匹配某些请求
                .permitAll() // 许可，即不需要通过认证就可以访问
                .anyRequest() // 任何请求，从执行效果来看，也可以视为：除了以上配置过的以外的其它请求
                .authenticated(); // 需要通过认证才可以访问

        // 是否调用以下方法，将决定是否启用默认的登录页面
        // 当未通过认证时，如果有登录页，则自动跳转到登录，如果没有登录页，则直接响应403
        http.formLogin();

        // super.configure(http); // 不要调用父类的同名方法，许多默认的效果都是父类方法配置的
    }

}