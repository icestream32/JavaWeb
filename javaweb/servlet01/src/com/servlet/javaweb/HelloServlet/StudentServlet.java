package com.servlet.javaweb.HelloServlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class StudentServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        // 设置文本响应类型
        servletResponse.setContentType("text/html");
        // 获取响应流
        PrintWriter out = servletResponse.getWriter();

        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;

        try {
            // 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取数据库操作对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","Mxb131452");
            String sql = "select empno,ename from emp";
            pt = conn.prepareStatement(sql);
            rs = pt.executeQuery();
            // 处理查询结果集
            while (rs.next()) {
                String no = rs.getString("empno");
                String name = rs.getString("ename");
                // 将结果输出到前端，因此需要使用HTMl语句换行
                out.print(no + "," + name + "<br>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pt != null) {
                try {
                    pt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
