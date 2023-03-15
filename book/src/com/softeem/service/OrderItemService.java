package com.softeem.service;

import com.softeem.bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemService {
    /**
     * 根据订单Id查询订单信息
     * @param orderId
     * @return
     * @throws SQLException
     */
    public List<OrderItem> queryBookByOrderId(String orderId) throws SQLException;
}
