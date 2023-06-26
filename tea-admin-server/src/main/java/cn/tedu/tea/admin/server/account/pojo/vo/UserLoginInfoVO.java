package cn.tedu.tea.admin.server.account.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户登录结果的VO类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class UserLoginResultVO implements Serializable {

    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像
     */
    private String avatar;
    /**
     * Token（JWT）
     */
    private String token;

}
