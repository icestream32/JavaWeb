package com.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class Filter1 implements Filter {

    public Filter1() {
        System.out.println("Filter1构造方法执行了！");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("doGet方法执行了！");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init方法执行了！");
    }

    @Override
    public void destroy() {
        System.out.println("destroy方法执行了！");
    }
}
