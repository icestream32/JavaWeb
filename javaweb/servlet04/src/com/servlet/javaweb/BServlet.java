package com.servlet.javaweb;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        ServletContext context = this.getServletContext();
        out.print("BServlet中的ServletContext对象：" + context);

        // 获取上下文的初始化参数
        Enumeration<String> names = context.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = context.getInitParameter(name);
            out.print("<br>" + name + "=" + value);
        }

        // 获取应用的根路径
        String contextPath = context.getContextPath();
        out.print("<br>" + contextPath); // /servlet04

        // 获取文件的绝对路径（真实路径）
        String realPath = context.getRealPath("index.html");
        out.print("<br>" + realPath); // D:\JavaDevelopment\JavaWeb\javaweb\out\artifacts\servlet04_war_exploded\index.html

    }
}
