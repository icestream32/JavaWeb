<%@ page import="com.servlet.javaweb.bean.User" %>
<%@ page import="com.servlet.javaweb.bean.Address" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--EL表达式的使用--%>
<%
    // 创建User对象
    User user = new User();
    user.setUsername("jackson");
    user.setPassword("1234");
    user.setAge(50);

    // 创建Address对象
    Address addr = new Address();
    addr.setCity("北京");
    addr.setStreet("朝阳区");

    user.setAddr(addr);

    // 将User对象存储到某个域当中，一定要存，因为EL表达式只能从某个范围中取数据。
    // 数据是必须存储到四大范围之一的。
    request.setAttribute("userObj",user);
%>

<%--使用EL表达式取--%>
${userObj}
<%--以上取出属性等同于Java代码:request.getAttribute("userObj")--%>
<br>
<%--如果想输出对象的属性值，怎么办？--%>
${userObj.username}
<br>
${userObj.password}
<br>
${userObj.age}
<br>
<%--
在以上EL表达式当中，这个语法，实际上调用了底层的getXxx()方法。
注意：如果没有对应的get方法，则出现异常，报500错误。
--%>
${userObj.addr}
<%--以上代码相当于user.getAddr().getCity()--%>

<br>

<%--EL表达式取数据的时候有两种形式--%>
${userObj.addr}<br>
${userObj["addr"]}<br>