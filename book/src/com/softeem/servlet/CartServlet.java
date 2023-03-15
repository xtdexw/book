package com.softeem.servlet;

import com.softeem.bean.CartItem;
import com.softeem.bean.Tbook;
import com.softeem.service.BookService;
import com.softeem.service.Cart;
import com.softeem.service.impl.BookServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {
    /**
     *加入购物车
     *@param request
     *@param response
     *@throws ServletException
     *@throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        HttpSession session = request.getSession();
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        try {
            // 通过主键id获取图书对象：tbook
            Tbook tbook = bookService.queryBookById(id);
            // 把图书信息，转换成为CartItem 商品项
            CartItem cartItem = new CartItem(tbook.getId(),tbook.getName(),1,tbook.getPrice(),tbook.getPrice());
            // 从session会话域中取出cart，如果为null则表示购物车无商品信息，否则有
            Cart cart = (Cart) session.getAttribute("cart");
            session.setAttribute("lastName",tbook.getName());
            if(cart == null){
                cart = new Cart();
                session.setAttribute("cart",cart);
            }
            //添加商品项
            cart.addItem(cartItem);

            //System.out.println(cart);
            //System.out.println("请求头 Referer 的值：" + request.getHeader("Referer"));

            response.sendRedirect(request.getHeader("Referer"));

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     *删除商品项
     *@param request
     *@param response
     *@throws ServletException
     *@throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        HttpSession session = request.getSession();
        //获取购物车对象
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null){
            cart.deleteItem(id);
        }
        //重定向回到原来购物车展示的页面
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     *清空商品项
     *@param request
     *@param response
     *@throws ServletException
     *@throws IOException
     */
    protected void clearItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        //获取购物车对象
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null){
            cart.clear();
        }
        //重定向回到原来购物车展示的页面
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     *修改商品数量
     *@param request
     *@param response
     *@throws ServletException
     *@throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取请求的参数 商品编号、商品数量
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null){
            cart.updateCount(id,count);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }
}
