package com.oa.web.action;

import com.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/dept/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 接受前端获取的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 连接数据库验证
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where username = ? and password = ?";
            pt = conn.prepareStatement(sql);
            pt.setString(1,username);
            pt.setString(2,password);
            rs = pt.executeQuery();

            // 判断用户是否存在
            if (rs.next()) {
                // 重定向至部门列表
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/loginError.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,rs);
        }

    }
}
