package com.oa.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {


        // 实行登录检查功能
        /*
        * 这里有一个问题需要注意：
        *   用户真正登录不能实施登录检查
        *   用户已经登录不能实施登录检查
        *   用户访问index.jsp不能拦截
        *   用户访问WelcomeServlet也不能拦截
         */
        // 强转成HttpServlet
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 在这里实现用户的登录检查和设置响应和请求字符集
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        String servletPath = request.getServletPath();

        if (session != null && session.getAttribute("username") != null) {
            // 进行下一步操作
            chain.doFilter(request,response);
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
