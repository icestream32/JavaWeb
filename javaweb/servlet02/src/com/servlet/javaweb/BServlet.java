package com.servlet.javaweb;

import jakarta.servlet.*;

import java.io.IOException;

public class BServlet implements Servlet {
    public BServlet() {
        System.out.println("BServlet构造方法执行了！");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
