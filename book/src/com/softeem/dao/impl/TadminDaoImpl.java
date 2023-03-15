package com.softeem.dao.impl;

import com.softeem.bean.Tadmin;
import com.softeem.dao.TadminDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

public class TadminDaoImpl extends BaseDao implements TadminDao {
    @Override
    public List<Tadmin> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Tadmin tadmin) throws SQLException {
        queryRunner.update("insert into t_admin values(null,?,?)",tadmin.getUsername(),tadmin.getPassword());
    }

    @Override
    public void updateById(Tadmin tadmin) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public Tadmin findById(Integer id) throws SQLException {
        BeanHandler<Tadmin> handler = new BeanHandler<>(Tadmin.class,hump());
        Tadmin query = queryRunner.query("select * from t_admin where id = ?", handler, id);
        return query;
    }

    @Override
    public List<Tadmin> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }

    @Override
    public Tadmin queryUserByUsernameAndPassword(Tadmin tadmin) throws SQLException {
        String sql = "select * from t_admin where username = ? and password = ?";
        return queryRunner.query(sql, new BeanHandler<>(Tadmin.class,hump()),tadmin.getUsername(),tadmin.getPassword());
    }

//    @Override
//    public Tadmin queryUserByUsername(String username) throws SQLException {
//        String sql = "select * from t_admin where username = ?";
//        return queryRunner.query(sql, new BeanHandler<>(Tadmin.class,hump()),username);
//    }


}
