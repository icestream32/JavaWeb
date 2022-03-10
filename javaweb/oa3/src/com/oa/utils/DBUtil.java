package com.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * 数据库连接工具类
 */
public class DBUtil {

    // 获取资源绑定器
    private static final ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

    private static final String driver = bundle.getString("driver");
    private static final String url = bundle.getString("url");
    private static final String username = bundle.getString("user");
    private static final String password = bundle.getString("password");

    /*
      注册驱动
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return 数据库连接对象
     * @throws SQLException 连接异常
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    /**
     * 释放资源
     * @param conn 数据库连接对象
     * @param stmt 数据库操作对象
     * @param rs 处理结果集
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
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
