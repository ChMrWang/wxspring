<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<div align="center">
		<div style="width: 400px; height: 200px; border: 1px solid red;">
			<h3>欢迎登陆问卷调查系统V1.0&nbsp;请登陆</h3>
			<form action="login.do" method="post">
				<label for="username" id="username">用户名</label> <input type="text"
					name="username"> <br>
				<br> <label for="password" id="password">密码</label> <input
					type="password" name="password"><br> <input
					type="submit" value="登录"> <a href="javascript:;">点此注册</a>
			</form>
		</div>
	</div>

</body>
</html>