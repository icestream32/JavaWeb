package com.servlet.javaweb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 设置请求域数据
        Date date = new Date();
        request.setAttribute("sysTime",date);

        // 获取请求域数据
        Object nowTime = request.getAttribute("sysTime");
        out.print("系统当前时间是：" + nowTime); // Sun Mar 06 10:03:22 HKT 2022

        // 转发（一次请求）
        // 获取转发器对象
        /*RequestDispatcher dispatcher = request.getRequestDispatcher("/b");
        // 使用转发器的forward方法实现跳转/转发
        dispatcher.forward(request,response);*/

        // 可以使用一行代码实现转发
        // request.getRequestDispatcher("/b").forward(request,response);

        // 获取客户端的IP地址
        String addr = request.getRemoteAddr();
        System.out.println(addr); // 127.0.0.1

        // 获取应用的根路径
        String contextPath = request.getContextPath();
        System.out.println(contextPath); // /servlet06

        // 获取请求方式
        String method = request.getMethod();
        System.out.println(method); // GET

        // 获取请求的URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI); // /servlet06/a

        // 获取Servlet Path
        String servletPath = request.getServletPath();
        System.out.println(servletPath); // /a

    }
}
