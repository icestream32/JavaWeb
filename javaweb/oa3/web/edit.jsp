<%@ page import="com.oa.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>修改部门</title>
	</head>
	<body>
		<h1>修改部门</h1>
		<hr>
		<%--在这里获取后端发送过来的信息--%>
		<%
			String deptno = request.getParameter("deptno");
			Dept dept = (Dept) request.getAttribute("dept");
		%>
		<form action="<%=request.getContextPath()%>/dept/update" method="post">
			部门编号<input type="text" name="deptno" value="<%=deptno%>" readonly="" /><br>
			部门名称<input type="text" name="dname" value="<%=dept.getDname()%>"/><br>
			部门位置<input type="text" name="loc" value="<%=dept.getLoc()%>"/><br>
			<input type="submit" value="修改"/><br>
		</form>
	</body>
</html>
