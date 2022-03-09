package com.oa.action.web;

import com.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 改造oa项目
 * 1、利用注解注册Servlet
 * 2、将部门功能对应的六个类封装到一个类中，也就是说。
 *  一个业务对应一个类，防止类爆炸。
 */
// 模糊匹配
@WebServlet("/dept/*")
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 获取应用路径,/dept
        String servletPath = request.getServletPath();
        // 获取功能路径,/edit
        String pathInfo = request.getPathInfo();
        // 拼接字符串
        StringBuilder stringBuffer = new StringBuilder(servletPath);
        stringBuffer.append(pathInfo);
        String path = stringBuffer.substring(0); // 这样path中的字符串即为"/dept/*"的形式了

        if ("/dept/list".equals(path)) {
            doList(request,response);
        } else if ("/dept/add".equals(path)){
            doAdd(request,response);
        } else if ("/dept/edit".equals(path)){
            doEdit(request,response);
        } else if ("/dept/update".equals(path)){
            doUpdate(request,response);
        } else if ("/dept/delete".equals(path)){
            doDel(request,response);
        } else if ("/dept/detail".equals(path)){
            doDetail(request,response);
        }


    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                out.println("                <a href='"+contextPath+"/dept/edit?deptno="+deptno+"'>修改</a>");
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
        out.println("    <a href='"+contextPath+"/add.html'>新增部门</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取前端的部门信息
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 连接数据库更新部门信息
        Connection conn = null;
        PreparedStatement pt = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update dept set dname = ?,loc = ? where deptno = ?";
            pt = conn.prepareStatement(sql);
            pt.setString(1,dname);
            pt.setString(2,loc);
            pt.setString(3,deptno);
            count = pt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,null);
        }

        // 返回列表界面
        if (count == 1) {
            response.sendRedirect("/oa/dept/list");
        } else {
            response.sendRedirect("/oa/error.html");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取前端传过来的部门编号
        String deptno = request.getParameter("deptno");

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
