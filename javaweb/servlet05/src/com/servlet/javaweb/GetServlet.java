package com.servlet.javaweb;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class GetServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 往服务器中响应一些内容
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <title>from post servlet</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>from post servlet</h1>");
        out.println("    </body>");
        out.println("</html>");

    }
}
