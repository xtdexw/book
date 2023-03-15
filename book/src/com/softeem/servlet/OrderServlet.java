package com.softeem.servlet;

import com.softeem.bean.Order;
import com.softeem.bean.User;
import com.softeem.service.Cart;
import com.softeem.service.OrderService;
import com.softeem.service.impl.OrderServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.Page;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     *生成订单
     *
     *@param request
     *@param response
     *@throws ServletException
     *@throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if(user == null){
            request.setAttribute("msg","登入超时");
            request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
        }

        try {
            //生成订单
            String orderId = orderService.createOrder(cart, user.getId());
            session.setAttribute("orderId",orderId);
            //防止表单重复提交，使用重定向

            response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *分页查询
     *
     *@param request
     *@param response
     *@throws ServletException
     *@throws IOException
     */
    protected void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), 4);
        try {
            Page<Order> page = orderService.page(pageNo, pageSize);
            request.setAttribute("page",page);
            page.setUrl("OrderServlet?action=listOrder");
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
