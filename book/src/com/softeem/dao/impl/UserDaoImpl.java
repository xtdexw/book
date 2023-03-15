package com.softeem.dao.impl;

import com.softeem.bean.User;
import com.softeem.dao.UserDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) throws SQLException {
        String sql = "select * from t_user where username = ?";
        return queryRunner.query(sql, new BeanHandler<>(User.class,hump()),username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from t_user where username = ? and password = ?";
        return queryRunner.query(sql, new BeanHandler<>(User.class,hump()),username,password);
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = "insert into t_user values(null,?,?,?)";
        //queryRunner.update(sql, user.getUsername(),user.getPassword(),user.getEmail());
        //添加成功后获取主键
        Long id = queryRunner.insert(sql, new ScalarHandler<>(), user.getUsername(), user.getPassword(), user.getEmail());
        user.setId(id.intValue());
    }
    @Override
    public List<User> findAll() throws SQLException {
        String sql = "select * from t_user";
        BeanListHandler<User> handler = new BeanListHandler<>(User.class,hump());
        List<User> query = queryRunner.query(sql, handler);
        return query;
    }

    @Override
    public void updateById(User user) throws SQLException {
        String sql = "update t_user set username = ?,password = ?,email = ? where id = ? ";
        queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getId());
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "delete from t_user where id = ?";
        queryRunner.update(sql,id);
    }

    @Override
    public User findById(Integer id) throws SQLException {
        String sql = "select * from t_user where id = ?";
        BeanHandler<User> handler = new BeanHandler<>(User.class,hump());
        User query = queryRunner.query(sql, handler, id);
        return query;
    }

    @Override
    public List<User> page(Integer pageNumber) throws SQLException {
        String sql = "select * from t_user limit ?,?";
        BeanListHandler<User> handler = new BeanListHandler<>(User.class,hump());
        List<User> query = queryRunner.query(sql, handler, (pageNumber - 1) * pageSize, pageSize);
        return query;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        String sql = "select count(*) from t_user";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long query = queryRunner.query(sql, handler);
        return query.intValue();
    }
}
