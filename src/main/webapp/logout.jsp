<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		session.removeAttribute("user_id");
		response.sendRedirect("loginMain.jsp");
	%>
</body>
</html>