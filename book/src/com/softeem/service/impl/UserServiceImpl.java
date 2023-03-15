package com.softeem.service.impl;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.dao.impl.UserDaoImpl;
import com.softeem.service.UserService;

import java.sql.SQLException;

//服务层【业务层】
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) throws SQLException {
        userDao.save(user);
    }

    @Override
    public User login(User user) throws SQLException {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    /**
     *检查 用户名是否可用
     *@param username
     *@return 返回true 表示用户名已存在，返回false 表示用户名可用
     */
    @Override
    public boolean existsUsername(String username) throws SQLException {
        return userDao.queryUserByUsername(username) != null;
    }
}
