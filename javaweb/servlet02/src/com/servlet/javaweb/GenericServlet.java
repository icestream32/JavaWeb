package com.servlet.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class GenericServlet extends jakarta.servlet.GenericServlet {
    /*
    * 编写一个GenericServlet类，这个类是一个抽象类，其中有一个抽象方法service。
        - GenericServlet实现Servlet接口。
        - GenericServlet是一个适配器。
        - 以后编写的所有Servlet类继承GenericServlet，重写service方法即可。
      */
    // 如果之后有需求必须要重写init方法，继承无参数的init方法即可

    @Override
    public void init() throws ServletException {
        System.out.println("重写的init方法");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("重写的service方法");
    }
}
