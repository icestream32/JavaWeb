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

public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取前端传过来的部门编号
        String deptno = request.getParameter("deptno");
        // 获取当前项目名字
        String contextPath = request.getContextPath();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>部门详情页面</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1>部门详情</h1>");
        out.println("    <hr>");


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
                out.println("                部门编号："+deptno+" <br>");
                out.println("                部门名称："+dname+" <br>");
                out.println("        部门位置："+loc+" <br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,rs);
        }
        out.println("        <input type='button' value='后退' onclick='window.history.back()'>");
        out.println("    <hr>");
        out.println("</body>");
        out.println("</html>");
    }
}
