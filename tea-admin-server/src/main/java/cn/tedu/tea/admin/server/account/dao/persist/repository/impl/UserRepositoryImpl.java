package cn.tedu.tea.admin.server.account.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.account.dao.persist.mapper.UserMapper;
import cn.tedu.tea.admin.server.account.dao.persist.repository.IUserRepository;
import cn.tedu.tea.admin.server.account.pojo.entity.User;
import cn.tedu.tea.admin.server.account.pojo.vo.UserListItemVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserLoginInfoVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.util.PageInfoToPageDataConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 处理用户数据的存储库实现类
 *
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
    public int insert(User user) {
        log.debug("开始执行【插入用户】的数据访问，参数：{}", user);
        return userMapper.insert(user);
    }

    @Override
    public int insertBatch(List<User> userList) {
        log.debug("开始执行【批量插入用户】的数据访问，参数：{}", userList);
        return userMapper.insertBatch(userList);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("开始执行【根据ID删除用户】的数据访问，参数：{}", id);
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("开始执行【批量删除用户】的数据访问，参数：{}", idList);
        return userMapper.deleteBatchIds(idList);
    }

    @Override
    public int updateById(User user) {
        log.debug("开始执行【更新用户】的数据访问，参数：{}", user);
        return userMapper.updateById(user);
    }

    @Override
    public int count() {
        log.debug("开始执行【统计用户的数量】的数据访问，参数：无");
        return userMapper.selectCount(null);
    }

    @Override
    public int countByUsername(String username) {
        log.debug("开始执行【根据用户名统计用户的数量】的数据访问，参数：{}", username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByPhone(String phone) {
        log.debug("开始执行【根据手机号码统计用户的数量】的数据访问，参数：{}", phone);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByEmail(String email) {
        log.debug("开始执行【根据电子邮箱统计用户的数量】的数据访问，参数：{}", email);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public UserStandardVO getStandardById(Long id) {
        log.debug("开始执行【根据ID查询用户详情】的数据访问，参数：{}", id);
        return userMapper.getStandardById(id);
    }

    @Override
    public UserLoginInfoVO getLoginInfoByUsername(String username) {
        log.debug("开始执行【根据用户名查询用户登录信息】的数据访问，参数：{}", username);
        return userMapper.getLoginInfoByUsername(username);
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询用户列表】的数据访问，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<UserListItemVO> list = userMapper.list();
        PageInfo<UserListItemVO> pageInfo = new PageInfo<>(list);
        PageData<UserListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

}
