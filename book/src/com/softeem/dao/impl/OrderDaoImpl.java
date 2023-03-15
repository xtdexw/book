package com.softeem.dao.impl;

import com.softeem.bean.Order;
import com.softeem.dao.OrderDao;
import com.softeem.utils.BaseDao;
import com.softeem.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public List<Order> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Order order) throws SQLException {
//        Connection conn = JdbcUtils.getConnection();
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        queryRunner.update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),
                order.getStatus(),order.getUserId());
    }

    @Override
    public void updateById(Order order) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public Order findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Order> page(Integer pageNumber) throws SQLException {
        BeanListHandler<Order> handler = new BeanListHandler<>(Order.class,hump());
        List<Order> query = queryRunner.query("select * from t_order order by create_time desc limit ?,?", handler, (pageNumber - 1) * pageSize, pageSize);
        return query;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long query = queryRunner.query("select count(*) from t_order", handler);
        return query.intValue();
    }
}
