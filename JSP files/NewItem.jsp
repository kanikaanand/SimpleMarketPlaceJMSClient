<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form name="createNewItemForm" action="HomeServlet"
		method="POST">
		<table>
			<tr>
				<td>Enter the item name</td>
				<td><input type="text" name="itemname" /></td>
			</tr>
			<tr>
				<td>Enter the item description</td>
				<td><input type="text" name="itemdesc" /></td>
			</tr>
			<tr>
				<td>Enter the sellerinfo</td>
				<td><input type="text" name="sellerinfo" /></td>
			</tr>
			<tr>
				<td>Enter the item price</td>
				<td><input type="text" name="itemprice" /></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="postad" value="Create a new item to sell"></td>
			</tr>
		</table>
	</form>

</body>
</html>