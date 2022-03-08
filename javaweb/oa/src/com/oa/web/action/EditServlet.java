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
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取前端信息
        String deptno = request.getParameter("deptno");
        // 打印前端页面
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>修改部门信息页面</title>");
        out.println("<head>");
        out.println("<body>");
        out.println("    <h1>输入需要修改的部门信息</h1>");
        out.println("    <hr>");

        // 连接数据库更新信息
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno = ?";
            pt = conn.prepareStatement(sql);
            pt.setString(1,deptno);
            rs = pt.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.println("    <form action='/oa/dept/update' method='post'>");
                out.println("                部门编号：<input type='text' value='"+deptno+"' name='deptno' readonly><br>");
                out.println("                部门名称：<input type='text' value='"+dname+"' name='dname'><br>");
                out.println("                部门位置：<input type='text' value='"+loc+"' name='loc'><br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,rs);
        }

        out.println("        <input type='submit' value='修改'>");
        out.println("    </form>");
        out.println("</body>");
        out.println("</html>");
    }
}
