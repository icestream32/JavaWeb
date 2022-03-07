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

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String contextPath = request.getContextPath();

        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>部门列表页面</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<script>");
        out.println("        function del(dno) {");
        out.println("    if (window.confirm('亲，删除之后不可恢复哦！')) {");
        out.println("        document.location.href = '/oa/dept/delete?deptno=' + dno");
        out.println("    }");
        out.println("}");
        out.println("</script>");

        out.println("    <h1 align='center'>部门列表</h1>");
        out.println("    <hr>");
        out.println("    <table border='1px' align='center' width='50%'>");
        out.println("        <tr>");
        out.println("            <th>序号</th>");
        out.println("            <th>部门编号</th>");
        out.println("            <th>部门名称</th>");
        out.println("            <th>操作</th>");
        out.println("        </tr>");
        out.println("        <tr>");
        /*以上代码是写死的*/

        try {
            // 获取数据库连接对象
            conn = DBUtil.getConnection();
            // 执行SQL语句
            String sql = "select * from dept";
            pt = conn.prepareStatement(sql);
            rs = pt.executeQuery();
            int i = 0;
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                out.println("            <td>"+(++i)+"</td>");
                out.println("            <td>"+deptno+"</td>");
                out.println("            <td>"+dname+"</td>");
                out.println("            <td>");
                out.println("                <a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
                out.println("                <a href='edit.html'>修改</a>");
                out.println("                <a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>详情</a>");
                out.println("            </td>");
                out.println("        </tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,rs);
        }

        /*以下代码是写死的*/
        out.println("    </table>");
        out.println("    <hr>");
        out.println("    <a href='add.html'>新增部门</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
