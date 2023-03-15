package com.softeem.servlet;

import com.softeem.bean.Order;
import com.softeem.bean.OrderItem;
import com.softeem.service.OrderItemService;
import com.softeem.service.impl.OrderItemServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.Page;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderItemServlet", value = "/OrderItemServlet")
public class OrderItemServlet extends BaseServlet {

    private OrderItemService orderItemService = new OrderItemServiceImpl();

    /**
     *查询订单详细
     *
     *@param request
     *@param response
     *@throws ServletException
     *@throws IOException
     */
    protected void checkOrderItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        try {
            List<OrderItem> orderItems = orderItemService.queryBookByOrderId(orderId);
            request.setAttribute("orderItem",orderItems);
            request.getRequestDispatcher("/pages/order/order_item.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
