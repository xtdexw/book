package com.softeem.test;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.dao.impl.UserDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImplTest {

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDaoImpl();
    }

    @Test
    public void queryUserByUsername() throws SQLException {
        User user = userDao.queryUserByUsername("admin");
        System.out.println("user = " + user);
    }

    @Test
    public void queryUserByUsernameAndPassword() {

    }

    @Test
    public void save() {
    }

    @Test
    public void findAll() throws SQLException {
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void updateById() throws SQLException {
        User user = new User();
        user.setUsername("xiaoxiaowen"); user.setPassword("123123");
        user.setEmail("abv1@qq.com"); user.setId(4);
        userDao.updateById(user);
    }

    @Test
    public void deleteById() throws SQLException {
        userDao.deleteById(4);
    }

    @Test
    public void findById() throws SQLException {
        User byId = userDao.findById(1);
        System.out.println("byId = " + byId);
    }

    @Test
    public void page() throws SQLException {
        List<User> page = userDao.page(2);
        for (User user : page) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void pageRecord() throws SQLException {
        Integer integer = userDao.pageRecord();
        System.out.println("integer = " + integer);
    }
}