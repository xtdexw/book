package com.softeem.service.impl;

import com.softeem.bean.OrderItem;
import com.softeem.dao.OrderItemDao;
import com.softeem.dao.impl.OrderItemDaoImpl;
import com.softeem.service.OrderItemService;

import java.sql.SQLException;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    //订单项dao
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public List<OrderItem> queryBookByOrderId(String orderId) throws SQLException {
        return orderItemDao.queryBookByOrderId(orderId);
    }
}
