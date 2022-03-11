package com.servlet.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // Session翻译为会话，Session对象为会话域
        // 当前端页面第一次发送请求的时候服务端会创建一个session对象生成一个三十位的id
        // JSESSIONID=CDA3C200C48A3D56F10CA4074DA83108
        // 同时发给浏览器
        // 当第二次发送请求的时候，浏览器会将sessionId发送到服务器，服务器会自动在session列表（Map集合）中匹配session对象
        // 从而响应到浏览器中，这样先前浏览器的请求信息都会被保留下来
        // 直到浏览器关闭，Session对象销毁
        HttpSession session = request.getSession();
        out.println(session); // org.apache.catalina.session.StandardSessionFacade@72e4b0f7

        // Session对象也有方法，常用方法和其他域对象差不多。
        session.setAttribute("zhangsan",new Object());
    }
}
