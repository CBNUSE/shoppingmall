<%@page import="java.io.PrintWriter"%>
<%@page import="order.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>OrderProc</title>
</head>
<body>
<% 
	
	int plistnum = Integer.parseInt(request.getParameter("plistnum"));	
	String pname = request.getParameter("pname");
	int count = Integer.parseInt(request.getParameter("count"));
	int osprice = Integer.parseInt(request.getParameter("osprice"));
	int sid = Integer.parseInt(request.getParameter("sid"));
	
  	OrderDAO ODAO = new OrderDAO();	
	int result = ODAO.insertOrder(plistnum, pname, count, osprice, sid);
  
	if(result == 1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('주문서 저장완료')");
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