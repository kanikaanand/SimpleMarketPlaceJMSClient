<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="actionForm" action="RegisterServlet" method="POST">
		<table>
			<tr>
				<td>Enter your first name:</td>
				<td><input type="text" name="firstname" /></td>
			</tr>
			<tr>
				<td>Enter your last name:</td>
				<td><input type="text" name="lastname" /></td>
			</tr>
			<tr>
				<td>Enter your email:</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Enter your password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="signup" value="signup"></td>
			</tr>
		</table>
	</form>

</body>
</html>