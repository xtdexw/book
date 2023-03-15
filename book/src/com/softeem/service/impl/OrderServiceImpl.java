package com.softeem.service.impl;

import com.softeem.bean.CartItem;
import com.softeem.bean.Order;
import com.softeem.bean.OrderItem;
import com.softeem.bean.Tbook;
import com.softeem.dao.OrderDao;
import com.softeem.dao.OrderItemDao;
import com.softeem.dao.TbookDao;
import com.softeem.dao.impl.OrderDaoImpl;
import com.softeem.dao.impl.OrderItemDaoImpl;
import com.softeem.dao.impl.TbookDaoimpl;
import com.softeem.service.Cart;
import com.softeem.service.OrderService;
import com.softeem.utils.Page;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    //订单dao
    private OrderDao orderDao = new OrderDaoImpl();
    //订单项dao
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    //图书dao
    private TbookDao tbookDao = new TbookDaoimpl();

    /**
     * 生成一个订单
     * 1.添加一个订单数据到数据库中的order表
     * 2.此订单中至少有一个订单项，至多N个..所以要将订单项都添加到orderItem表中
     * 3.清空购物车数据。i
     * @param cart 购物车对象
     * @param userId 用户id
     * @return 返回订单的id
     */
    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        //1.添加一个订单数据到数据库中的order表
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setPrice(cart.getTotalPrice());
        order.setStatus(0);
        order.setUserId(userId);
        orderDao.save(order);

//        int i = 10 / 0;

        //2.此订单中至少有一个订单项，至多N个..所以要将所有订单项都添加到orderItem表中
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(entry.getValue().getName());//设置订单项的名字
            orderItem.setCount(entry.getValue().getCount());//设置订单项的数量
            orderItem.setPrice(entry.getValue().getPrice());//设置订单项的单价
            orderItem.setTotalPrice(entry.getValue().getTotalPrice());//设置订单项的总价
            orderItem.setOrderId(orderId);//设置订单项的外键id 订单编号
            orderItemDao.save(orderItem);

            //更新库存和销量
            Tbook tbook = tbookDao.findById(entry.getValue().getId());//通过图书的主键id查询一个图书对象
            tbook.setSales(tbook.getSales()+entry.getValue().getCount());//设置销量
            tbook.setStock(tbook.getStock()-entry.getValue().getCount());//设置库存
            tbookDao.updateById(tbook);//修改book的库存和销量
        }
        //3.清空购物车
        cart.clear();
        return orderId;
    }

    /**
     * 订单分页查询
     * @param begin 开始页数
     * @param pageSize 总页数
     * @return
     * @throws SQLException
     */
    @Override
    public Page<Order> page(Integer begin,Integer pageSize) throws SQLException {
        Page<Order> page = new Page<>();
        Integer totalCount = orderDao.pageRecord();//查询总记录数
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize-1) / pageSize);//求出总页数
        page.setPageNo(begin);//设置当前页
        page.setItems(orderDao.page(page.getPageNo()));//设置分页查询的结果集
        return page;
    }

}
