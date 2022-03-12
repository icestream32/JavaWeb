package com.oa.web.action;

import com.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Servlet负责业务的处理。
// JSP负责页面的展示。
@WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取类路径
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request,response);
        } else if ("/user/exit".equals(servletPath)) {
            doExit(request,response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 手动销毁Session对象
        HttpSession session = request.getSession();
        session.invalidate();
        // 重定向至登录页面
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 接受前端获取的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 连接数据库验证
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        // 打个布尔标记判断登录是否成功
        Boolean success = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where username = ? and password = ?";
            pt = conn.prepareStatement(sql);
            pt.setString(1,username);
            pt.setString(2,password);
            rs = pt.executeQuery();

            // 判断用户是否存在
            if (rs.next()) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,rs);
        }

        if (success) {
            // 获取session对象(这里的要求是：必须获取到session，没有session也要新建一个session对象。)
            HttpSession session = request.getSession();
            session.setAttribute("username",username);


            // 登录成功了，并且用户确实选择了“十天内免登录”功能。
            String value = request.getParameter("getCookie");
            if ("1".equals(value)) {
                // 创建Cookie对象，用于存储用户名和密码
                Cookie cookie1 = new Cookie("username",username);
                Cookie cookie2 = new Cookie("password", password);
                // 设置Cookie持续日期
                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);
                // 设置Cookie关联路径
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                // 将Cookie响应给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }

            // 成功，跳转至登录页面
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 跳转到登录失败页面内
            response.sendRedirect(request.getContextPath() + "/loginError.html");
        }

    }
}
