<%@page import="product.Bean"%>
<%@page import="product.PrDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="bid.bid_bean"%>
<%@page import="bid.BidDAO"%>
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
	int product_id = Integer.parseInt(request.getParameter("product_id"));
	String Biddingprice = request.getParameter("Biddingprice");
	int price = Integer.parseInt(Biddingprice);
	String id = null;
	
	PrDAO PDAO = new PrDAO();
	Bean bean = PDAO.getProduct2(product_id);
	int startprice = bean.getPrice();
	
	if(startprice <= price){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�������� ���۱ݾ׺��� �����ϴ�!');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	if(session.getAttribute("user_id") != null){
			id = (String) session.getAttribute("user_id");
	}
	
	BidDAO BDAO = new BidDAO();
	
	bid_bean bbean = BDAO.getLowest(product_id);
	int Lprice = bbean.getPrice();
	
	if(price >= Lprice){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�������� ���� �ּ������׺��� �����ϴ�!');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	PDAO.incleaseViews2(product_id);
	BDAO.bidding2(product_id, price, id);
	
	
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('��������! ��������ϴ�.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
%>
</body>
</html>