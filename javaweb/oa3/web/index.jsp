<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>欢迎使用OA系统</title>
	</head>
	<body>
		<h1>LOGIN PAGE</h1>
		<hr>
		<form action="<%=request.getContextPath()%>/dept/user" method="post">
			<label>username</label><input type="text" name="username" > <br>
			<label>password</label><input type="password" name="password"><br>
			<input type="submit" value="LOGIN" >
		</form>
	</body>
</html>
