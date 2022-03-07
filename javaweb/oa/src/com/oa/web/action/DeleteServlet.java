package com.oa.web.action;

import com.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取前端传入的部门编号信息
        String deptno = request.getParameter("deptno");

        // 连接数据库进行DML操作
        Connection conn = null;
        PreparedStatement pt = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            // 涉及到DML语句都需要开启事务
            conn.setAutoCommit(false);
            String sql = "delete from dept where deptno = ?";
            pt = conn.prepareStatement(sql);
            pt.setString(1,deptno);
            count = pt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,null);
        }
        // 判断是否删除成功，成功则转发至部门列表页面，失败则转发至失败页面
        if (count == 1) {
            request.getRequestDispatcher("/dept/list").forward(request,response);
        } else {
            request.getRequestDispatcher("/error.html").forward(request,response);
        }
    }
}
