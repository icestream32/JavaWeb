<%--<%@page contentType="text/html;charset=UTF-8" isELIgnored="false" %>--%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page contentType="text/html;charset=UTF-8" isELIgnored="true" %>

<%
  request.setAttribute("username", "zhangsan");
%>

<%-- isELIgnored="true" 表示忽略JSP中整个页面的所有EL表达式。如果想忽略其中某个，可以使用以下反斜杠。 --%>
<%--忽略了EL表达式，这个表达式就会变成普通字符串输出到浏览器上--%>
\${username}
${username}
${username}
