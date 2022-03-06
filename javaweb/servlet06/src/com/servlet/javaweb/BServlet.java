package com.servlet.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // request.setAttribute("sysTime",new Date());
        // 从请求域当中取出绑定的数据
        Object sysTime = request.getAttribute("sysTime");

        //输出到浏览器
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("系统当前时间是：" + sysTime);

        request.getRequestDispatcher("/a").forward(request,response);
    }
}
