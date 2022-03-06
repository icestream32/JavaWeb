package com.oa.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDBUtil {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            // 执行SQL语句
            String sql = "select * from dept";
            pt = conn.prepareStatement(sql);
            rs = pt.executeQuery();
            int i = 0;
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                System.out.println(deptno + "," + dname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
