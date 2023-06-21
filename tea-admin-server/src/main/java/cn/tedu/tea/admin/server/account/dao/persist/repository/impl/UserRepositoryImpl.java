package cn.tedu.tea.admin.server.account.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.account.dao.persist.mapper.UserMapper;
import cn.tedu.tea.admin.server.account.dao.persist.repository.IUserRepository;
import cn.tedu.tea.admin.server.account.pojo.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserMapper userMapper;

    public UserRepositoryImpl() {
        log.info("创建存储库对象：UserRepositoryImpl");
    }

    @Override
    public UserLoginInfoVO getLoginInfoByUsername(String username) {
        log.debug("开始执行【根据用户名查询用户的登录信息】，参数：{}", username);
        return userMapper.getLoginInfoByUsername(username);
    }

}
