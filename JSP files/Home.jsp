<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>
</head>
<body>
	<form name="home" action="HomeServlet" method="POST">

		<%
			Cookie[] cookies = request.getCookies();
			String sessionID = null;
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("JSESSIONID"))
						sessionID = cookie.getValue();
				}
			}
		%>
		Login Successful
		<%
			String userString = (String) session.getAttribute("user");
			String[] userTokens = userString.split(";");
		%>
		Hello,
		<%=userTokens[1]%>
		You last logged in at
		<%=userTokens[5]%>
		<table border="1">
			<tr>
				<th>user ID</th>
				<th>ITEM NAME</th>
				<th>ITEM DESC</th>
				<th>SELLER INFO</th>
				<th>ITEM PRICE</th>
			</tr>

			<%
				String adString = (String) session.getAttribute("ads");
				if (!adString.equalsIgnoreCase("you are not subscribed to any ads")) {

					adString = adString.substring(0, adString.length() - 1);

					String[] adStringTokens = adString.split(";");
					int i = 0;
					int len = adStringTokens.length;
					for (i = 0; i < len; i++) {

						String[] adFields = adStringTokens[i].split(",");
			%>
			<tr>
				<td><%=adFields[0]%></td>
				<td><%=adFields[1]%></td>
				<td><%=adFields[2]%></td>
				<td><%=adFields[3]%></td>
				<td><%=adFields[4]%></td>
			</tr>


			<%
				}
			} else{
			%>
			<tr>no ads to display
			</tr>
			<% } %>
			<table>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						name="subscribe" value="subscribe"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						name="unsubscribe" value="unsubscribe"></td>
				</tr>
			</table>

			<a href="NewItem.jsp">Post a New Item to sell</a>

			</form>
</body>
</html>