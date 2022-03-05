package com.servlet.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HttpServletTest extends HttpServlet {
    // 重写doGet方法，当前端发起的都是get请求时，调用doGet方法中的内容
    // 如果前端发起的是其他请求时，会报405错误
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("Hello, welcome to HttpServlet!");
    }
}
