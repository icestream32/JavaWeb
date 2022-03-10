<%@ page import="com.oa.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>部门详情</title>
	</head>
	<%--在这里从后端获取部门信息--%>
	<%
		Dept dept = (Dept) request.getAttribute("dept");
		String deptno = request.getParameter("deptno");
	%>
	<body>
		<h1>部门详情</h1>
		<hr>
		部门编号：<%=deptno%> <br>
		部门名称：<%=dept.getDname()%><br>
		部门位置：<%=dept.getLoc()%><br>
		<input type="button" value="后退" onclick="window.history.back()"/>
	</body>
</html>
