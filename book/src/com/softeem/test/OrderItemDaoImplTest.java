package com.softeem.test;

import com.softeem.bean.OrderItem;
import com.softeem.dao.OrderItemDao;
import com.softeem.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void save() throws SQLException {
        OrderItem orderItem = new OrderItem(null,"java从入门到精通",1,new BigDecimal(10),new BigDecimal(10),"1234567891");
        orderItemDao.save(orderItem);
    }

    @Test
    public void findAll() {
    }


    @Test
    public void updateById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void page() {
    }

    @Test
    public void pageRecord() {
    }

    @Test
    public void queryBookByOrderId() throws SQLException {
        List<OrderItem> orderItems = orderItemDao.queryBookByOrderId("16599521384931");
        for (OrderItem orderItem : orderItems) {
            System.out.println("orderItem = " + orderItem);
        }
    }
}