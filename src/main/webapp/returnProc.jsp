<%@page import="java.io.PrintWriter"%>
<%@page import="buy.*"%>
<%@page import="refund.*"%>
<%@page import="user.*"%>
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
	session = request.getSession();
	String cid = (String)session.getAttribute("user_id");
	UserBean ubean = new UserDAO().retrieveCustomer(cid);	
	
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	String reason = request.getParameter("reason");
	
	java.util.Date utilDate = new java.util.Date(); 
	java.sql.Date rday = new java.sql.Date(utilDate.getTime()); 
	
	RefundDAO RDAO = new RefundDAO();
	int result = RDAO.insertRefund(cid, bnum, rday, reason);
	
	BuyDAO BDAO = new BuyDAO();
	BDAO.deleteBuy(bnum);
	
	if(result == 1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('환불신청 완료')");
		script.println("location.href = 'home.jsp'");
		script.println("</script>");
		script.close();
	}
	else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('에러 발생')");
		script.println("history.back();");
		script.println("</script>");
		script.close();
	}
%>
</body>
</html>