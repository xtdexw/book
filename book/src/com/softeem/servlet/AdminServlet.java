package com.softeem.servlet;

import com.softeem.bean.Tadmin;
import com.softeem.service.AdminService;
import com.softeem.service.impl.AdminServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends BaseServlet {

    public static void main(String[] args) {
        Properties pro = System.getProperties();
        System.out.println(pro.getProperty("user.home"));
    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //自动获取参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Tadmin tadmin = new Tadmin();
        WebUtils.copyParamToBean(parameterMap, tadmin);

        AdminService adminService = new AdminServiceImpl();

        try {
            Tadmin myadmin = adminService.login(tadmin);
            if (myadmin != null) {
                HttpSession session = request.getSession();//会话作用域
                session.setAttribute("admin", myadmin);
                request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);
            } else {
                request.setAttribute("msgtop", "账号名或登入密码不正确");
                request.setAttribute("username", request.getParameter("username"));
                request.setAttribute("password", request.getParameter("password"));
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
