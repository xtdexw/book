package com.softeem.dao;
;
import com.softeem.bean.OrderItem;
import com.softeem.utils.BaseInterface;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao extends BaseInterface<OrderItem> {
    /**
     * 根据订单Id查询订单信息
     * @param orderId
     * @return
     * @throws SQLException
     */
    public List<OrderItem> queryBookByOrderId(String orderId) throws SQLException;
}
