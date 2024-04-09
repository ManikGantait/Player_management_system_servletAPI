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
<%List<Player> list=(List<Player>)request.getAttribute("player"); %>

<form action="saveUpdate" method="post" >
<% for(Player p:list){ %>
<input type="number" name="playerId" value="<%=p.getPlayerId() %>"> <br><br>
<input type="name" name="playerName" value="<%=p.getPlayerName() %>"> <br><br>
<input type="number" name="playerAge" value="<%=p.getPlayerAge() %>"> <br><br>
<input type="text" name="playerCountry" value="<%=p.getPlayerCountry() %>"> <br><br>
<input type="text" name="iplTeam" value="<%=p.getIplTeam() %>">  <br><br>
<input type="number" name="salary" value="<%=p.getSalary() %>">
<input type="submit" value="update" >

<%} %>
</form>


</body>
</html>