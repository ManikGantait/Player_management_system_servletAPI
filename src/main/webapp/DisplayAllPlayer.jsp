<%@page import="com.jpa.iplteam.Player"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	List<Player> list = (List<Player>) request.getAttribute("playerList");
	%>
	<table border="">
		<tr>
			<th>PlayerId</th>
			<th>PlayerName</th>
			<th>PlayerAge</th>
			<th>PlayerCountry</th>
			<th>IplTeam</th>
			<th>Salary</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		
		<% for(Player p:list){ %>
		<tr>		
		<td><%=p.getPlayerId() %></td>
		<td><%= p.getPlayerName() %></td>
		<td><%= p.getPlayerAge() %></td>
		<td><%= p.getPlayerCountry() %></td>
		<td><%= p.getIplTeam() %></td>
		<td><%= p.getSalary() %></td>
		<td><a href="update?playerId=<%= p.getPlayerId()%>">update</a></td>
		<td><a href="delete?playerId=<%= p.getPlayerId()%>">delete</a></td>
		</tr>
			<%}  %>

	</table>

</body>
<a href="Index.jsp">Home</a>

</html>