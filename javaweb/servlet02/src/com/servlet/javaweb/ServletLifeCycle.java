package com.servlet.javaweb;

import jakarta.servlet.*;

import java.io.IOException;

public class ServletLifeCycle implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Servlet中的init方法执行了！");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        System.out.println("Servlet中的service方法执行了！");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Servlet中的destroy方法执行了！");
    }
}
