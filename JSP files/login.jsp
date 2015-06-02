<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login page</title>
<h1>Welcome to SIMPLE MARKET PLACE</h1>
</head>
<body>
	<form name="login" action="LoginServlet" method="POST"">
		<table>
			<tr>
				<td>Enter your Username:</td>
				<td><input type="text" name="uname" /></td>
			</tr>
			<tr>
				<td>Enter your Password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="signin" value="signin"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="newuser" value="New User? register now"></td>
			</tr>
		</table>
	</form>

</body>
</html>