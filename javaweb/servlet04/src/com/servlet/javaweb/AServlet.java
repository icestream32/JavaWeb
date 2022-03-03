package com.servlet.javaweb;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        ServletContext context = this.getServletContext();
        out.print("AServlet中的ServletContext对象：" + context);

        // 通过ServletContext对象也是可以记录日志的
        context.log("这是一个log日志");
    }
}
