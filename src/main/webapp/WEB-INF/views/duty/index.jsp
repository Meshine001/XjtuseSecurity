<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限验证</title>
</head>
<body>
	<form action="./duty/validate" method="post" enctype="application/x-www-form-urlencoded">
		Please input security code:<input type="password" name="securityCode">
		<input type="submit" value="Comfirm">
	</form>
</body>
</html>