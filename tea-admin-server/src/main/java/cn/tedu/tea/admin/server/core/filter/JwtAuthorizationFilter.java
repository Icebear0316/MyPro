package cn.tedu.tea.admin.server.core.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>处理JWT的过滤器</p>
 *
 * <p>此过滤器的主要作用：</p>
 * <ul>
 *     <li>尝试接收客户端的请求中携带的JWT数据</li>
 *     <li>尝试解析JWT数据</li>
 *     <li>将解析得到的用户数据创建为Authentication对象，并存入到SecurityContext中</li>
 * </ul>
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    public JwtAuthorizationFilter() {
        log.info("创建过滤器对象：JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("处理JWT的过滤器开始处理当前请求……");
        // 尝试接收客户端的请求中携带的JWT数据
        // 业内惯用的做法是：客户端会将JWT放在请求头中名为Authorization的属性中
        String jwt = request.getHeader("Authorization");
        log.debug("客户端携带的JWT：{}", jwt);

        // 尝试解析JWT数据

        // 将解析得到的用户数据创建为Authentication对象

        // 将Authentication对象存入到SecurityContext中

        // 过滤器链继续执行，即：放行
        filterChain.doFilter(request, response);
    }

}
