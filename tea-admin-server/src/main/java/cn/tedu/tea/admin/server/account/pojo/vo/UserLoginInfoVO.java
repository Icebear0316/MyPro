package cn.tedu.tea.admin.server.account.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class UserLoginInfoVO implements Serializable {

    /**
     * 数据id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（密文）
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;

}
