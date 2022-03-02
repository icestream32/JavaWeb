package com.servlet.javaweb;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ServletConfigTest extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // 因为继承的是GenericServlet类，在这个类中实现了ServletConfig当中的一些方法
        Enumeration<String> names = this.getInitParameterNames();
        while (names.hasMoreElements()) {
            String s = names.nextElement();
            String initParameter = this.getInitParameter(s);
            out.print(initParameter + "<br>");
        }
    }
}
