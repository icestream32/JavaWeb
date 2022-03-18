<%@ page import="com.oa.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>部门详情</title>
	</head>
	<body>
		<h1>部门详情</h1>
		<h3>欢迎${username}</h3>
		<hr>
		部门编号：${deptno} <br>
		部门名称：${dept.dname}<br>
		部门位置：${dept.loc}<br>
		<input type="button" value="后退" onclick="window.history.back()"/>
	</body>
</html>
