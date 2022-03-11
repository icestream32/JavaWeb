package com.oa.web.action;

import com.oa.bean.Dept;
import com.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        // 登录认证，获取Session对象
        // 获取session（这个session是不需要新建的）
        // 只是获取当前session，获取不到这返回null
        HttpSession session = request.getSession(false);

        response.setContentType("text/html;charset=UTF-8");
        // 如果当前登录用户不为空且会话域不为空，执行Servlet业务
        if (session != null && session.getAttribute("username") != null) {
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
            } else if ("/dept/update".equals(path)){
                doUpdate(request,response);
            } else if ("/dept/delete".equals(path)){
                doDel(request,response);
            } else if ("/dept/detail".equals(path)){
                doDetail(request,response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String contextPath = request.getContextPath();

        // 新建一个集合，用于存放部门信息
        List<Dept> deptList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;

        try {
            // 获取数据库连接对象
            conn = DBUtil.getConnection();
            // 执行SQL语句
            String sql = "select * from dept";
            pt = conn.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                // 新建一个对象将部门信息封装进去
                Dept dept = new Dept(deptno, dname, loc);
                // 将获得的对象放入集合当中
                deptList.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,rs);
        }

        // 将集合放入请求域中，转发到jsp中
        request.setAttribute("deptList",deptList);
        request.getRequestDispatcher("/list.jsp").forward(request,response);

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
            response.sendRedirect(""+request.getContextPath()+"/dept/list");
        } else {
            response.sendRedirect(""+request.getContextPath()+"/error.html");
        }
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
            response.sendRedirect(""+request.getContextPath()+"/dept/list");
        } else {
            response.sendRedirect(""+request.getContextPath()+"/error.html");
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

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 创建一个部门对象
        Dept dept = new Dept();

        // 获取前端传过来的部门编号
        String deptno = request.getParameter("deptno");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

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
                dept.setDname(dname);
                dept.setLoc(loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pt,rs);
        }

        // 通过获取参数选择转发到哪种页面
        String f = request.getParameter("f");
        // 将部门对象放入请求域中
        request.setAttribute("dept",dept);
        // 转发
        request.getRequestDispatcher("/" + f + ".jsp").forward(request,response);
    }

}
