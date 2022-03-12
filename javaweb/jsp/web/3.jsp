<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.servlet.javaweb.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<%--掌握使用EL表达式，怎么从Map集合中取数据--%>
<%--${map.key}--%>
<%
    // 一个Map集合
    Map<String,String> map = new HashMap<>();
    map.put("username", "zhangsan");
    map.put("password", "123");

    // 将Map集合存储到request域当中。
    request.setAttribute("userMap", map);

    Map<String, User> userMap2 = new HashMap<>();
    User user = new User();
    user.setUsername("lisi");
    userMap2.put("user", user);
    request.setAttribute("fdsafdsa", userMap2);
%>
${userMap.username}
${userMap.password}
<br>
${fdsafdsa.lisi}
${fdsafdsa.user}
