<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>新增部门</title>
	</head>
	<body>
		<h1>新增部门</h1>
		<h3>欢迎<%=session.getAttribute("username")%></h3>
		<hr>
		<form action="<%=request.getContextPath()%>/dept/add" method="post"/>
		<label>
			部门编号
			<input type="text" name="deptno"/>
		</label> <br>
		<label>
			部门名称
			<input type="text" name="dname"/>
		</label> <br>
		<label>
			部门位置
			<input type="text" name="loc"/>
		</label> <br>
			<input type="submit" value="保存"/> <br>
		</form>
	</body>
</html>
