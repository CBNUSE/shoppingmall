<%@page import="java.io.PrintWriter"%>
<%@page import="user.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("UTF-8");	
	
	session = request.getSession();
	String cid = (String)session.getAttribute("user_id");
	String caddress = request.getParameter("caddress");
	String ctel = request.getParameter("ctel");

	UserDAO UDAO = new UserDAO();
	UserBean ubean = new UserBean();
	
	ubean.setCid(cid);
	ubean.setCaddress(caddress);
	ubean.setCtel(ctel);
	
	UDAO.updateCustomer(ubean);
	
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('회원정보 수정 완료');");
	script.println("location.href = 'mypage.jsp'");
	script.println("</script>");
	script.close();
%>
</html>