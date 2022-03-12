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

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取Cookie
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        // 这个Cookie[]数组可能是null，如果不是null，数组的长度一定是大于0的
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
                if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }
        // 验证用户名和密码
        if (username != null && password != null) {
            Connection conn = null;
            PreparedStatement pt = null;
            ResultSet rs = null;
            boolean success = false;
            try {
                conn = DBUtil.getConnection();
                String sql = "select * from t_user where username = ? and password = ?";
                pt = conn.prepareStatement(sql);
                pt.setString(1,username);
                pt.setString(2,password);
                rs = pt.executeQuery();
                if (rs.next()) {
                    success = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,pt,rs);
            }

            // 跳转至部门列表或者登录界面
            if (success) {
                // 创建Session对象
                HttpSession session = request.getSession();
                session.setAttribute("username",username);

                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
