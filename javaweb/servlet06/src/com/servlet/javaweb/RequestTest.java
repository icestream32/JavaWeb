package com.servlet.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

public class RequestTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        * 获取前端的数据：
        *   一般有三种方法
        *   但是前两种在之后框架会经常用到，因此现在没学就不需要用
        *   最后一种是最常用的
        *   String getParameter(String name) 获取value这个一维数组当中的第一个元素。
        *   因为在开发中都知道在前端获取的是什么元素。
        *   参数可以写死，因为不常修改。
        * */
        // 第一种方法：Map<String,String[]> getParameterMap()
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries){
            String key = entry.getKey();
            System.out.print(key + "=");
            String[] value = entry.getValue();
            for (String v : value) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

        // 第二种方法：
        // Enumeration<String> getParameterNames()
        // String[] getParameterValues(String name)
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.print(name + "=");
            String[] value = request.getParameterValues(name);
            for (String v : value) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

        // 第三种方法：String getParameter(String name)
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        // 知道最后一个是数组，因此就使用另一个方法来接收
        String[] interest = request.getParameterValues("aihao");
        System.out.println("username" + "=" + userName);
        System.out.println("password" + "=" + password);
        System.out.print("aihao=");
        for (String v : interest) {
            System.out.print(v + " ");
        }

    }
}
