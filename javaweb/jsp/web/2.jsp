<%@ page contentType="text/html;charset=UTF-8"%>

<%--
EL表达式优先从小范围中读取数据
    pageContext<request<session<application
--%>
<%
    // pageContext.setAttribute("name","pageContext");
    request.setAttribute("name","request");
    session.setAttribute("name","session");
    application.setAttribute("name","application");
%>
${name} <%--request--%>
<br>

<%--
EL表达式中有四个隐含的隐式的范围：
    pageScope对应的是pageContext范围。
    requestScope对应的是request范围。
    sessionScope对应的是session范围。
    applicationScope对应的是application范围。
    通过隐式调用可以在指定范围内取出数据
--%>
${applicationScope.name} <%--application--%>
<%--
从上面一连串操作可以得知：
正常开发中不会使用同一个名字的。。。
--%>

<br>

<%--EL表达式对null进行了预处理，如果是null，则向浏览器输出一个空字符串--%>
<%--对比以下两个输出--%>
<%=request.getAttribute("n")%> <%--输出null--%>
${n} <%--啥也没有输出--%>
<%--这样子做可以提升前端界面的显示体验，让错误只是显示在后端--%>

<br>

