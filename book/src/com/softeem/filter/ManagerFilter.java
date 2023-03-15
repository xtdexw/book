package com.softeem.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.REQUEST},
        filterName = "ManagerFilter" ,urlPatterns = "/pages/manager/*")
public class ManagerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //强制类型转换
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //getSession()方法是HttpServletRequest类中特有的，ServletRequest没有，所以强转
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("admin");
        if( user == null ){
            httpServletRequest.getRequestDispatcher("/login.jsp").forward(request,response);
        }else {
            //让程序继续往下执行`
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
