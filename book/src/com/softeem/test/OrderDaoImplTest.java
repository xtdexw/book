package com.softeem.test;

import com.softeem.bean.Order;
import com.softeem.dao.OrderDao;
import com.softeem.dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;


public class OrderDaoImplTest {

    OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void save() throws SQLException {
        Order order = new Order("1234567891",new Timestamp(System.currentTimeMillis()),new BigDecimal(100),0, 1);
        orderDao.save(order);
    }
}