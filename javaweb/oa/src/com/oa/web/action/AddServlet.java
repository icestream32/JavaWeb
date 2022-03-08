package com.oa.web.action;

import com.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取前端信息
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 连接数据库存储信息
        Connection conn = null;
        PreparedStatement pt = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into dept(deptno, dname, loc) VALUES (?,?,?)";
            pt = conn.prepareStatement(sql);
            pt.setString(1,deptno);
            pt.setString(2,dname);
            pt.setString(3,loc);
            count = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,null);
        }

        // 返回list页面
        if (count == 1) {
            // 如果这里使用转发，那么这一次请求就会发送post请求，而list页面是get请求，会报405错误
            // 重定向
            response.sendRedirect("/oa/dept/list");
        } else {
            response.sendRedirect("/oa/error.html");
        }
    }
}
