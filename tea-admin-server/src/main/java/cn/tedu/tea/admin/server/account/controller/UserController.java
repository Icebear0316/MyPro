package cn.tedu.tea.admin.server.account.controller;

import cn.tedu.tea.admin.server.account.service.IUserService;
import cn.tedu.tea.admin.server.common.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public JsonResult login() {
        System.out.println("处理登录请求");
        userService.login();
        return JsonResult.ok();
    }

}
