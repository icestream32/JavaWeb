package com.servlet.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 创建一个Cookie对象
        Cookie cookie = new Cookie("zhangsan", "123");
        // 设置Cookie对象的有效时间
        /*
        从Cookie的源码注释可以知道
        当设置的数字大于0时，以每秒为单位设置，将Cookie保存到硬盘中
        当设置的数字等于0时，删除Cookie
        当设置的数字小于0时，将Cookie保存到缓存中，相当于没设置Cookie的有效时间
         */
        // cookie.setMaxAge(60 * 60);
        // cookie.setMaxAge(0);
        cookie.setMaxAge(-1);
        // 往浏览器中响应cookie
        response.addCookie(cookie);
        /*
        关于Cookie的关联路径
        假设现在发送的请求路径是“http://localhost:8080/servlet13/cookie/generate”生成的cookie，如果cookie没有设置path，默认的path是什么？
            默认的path是：http://localhost:8080/servlet13/cookie 以及它的子路径。
            也就是说，以后只要浏览器的请求路径是http://localhost:8080/servlet13/cookie 这个路径以及这个路径下的子路径，cookie都会被发送到服务器。
         */
        // 手动设置Cookie的关联路径
        cookie.setPath("/servlet11"); // 表示只要是这个servlet11项目的请求路径，都会提交这个cookie给服务器。
    }
}
