<%@page contentType="text/html; charset=UTF-8"%>
<%--JSP内置对象之一：Session对象--%>
<%--可以在这里禁用自动生成Session对象--%>
<%@page session="false" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>欢迎使用OA系统</title>
	</head>
	<body>
		<h1>LOGIN PAGE</h1>
		<hr>
		<form action="<%=request.getContextPath()%>/user/login" method="post">
			<label>username</label><input type="text" name="username" > <br>
			<label>password</label><input type="password" name="password"><br>
			<input type="submit" value="LOGIN" >
		</form>
	</body>
</html>
