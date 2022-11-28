<%@page import="java.io.PrintWriter"%>
<%@page import="buy.*"%>
<%@page import="product.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");	
	
	int btotal = Integer.parseInt(request.getParameter("buyTotal"));
	int pnum = Integer.parseInt(request.getParameter("pnum"));
	String cid = (String)session.getAttribute("user_id");
	
	PrDAO PDAO = new PrDAO();
	ProductBean pbean = new ProductBean();
	pbean = PDAO.retrieveProduct(pnum);

	//재고 확인
	int ptotal = pbean.getPtotal();
	if(btotal > ptotal){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('구매 수량이 현재 남아있는 재고량보다 많습니다!');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
	}
	
	int bprice = pbean.getPprice() * btotal;
	
	java.util.Date utilDate = new java.util.Date(); //현재 날짜(자바 객체)
	java.sql.Date date = new java.sql.Date(utilDate.getTime()); //sql date객체로 변환
	
	BuyDAO BDAO = new BuyDAO();
	int result = BDAO.buyProduct(pnum, cid, bprice, btotal, date);
	
	if(result == 1){
		//구매수량만큼 재고 업데이트
		PDAO.updateProductTotal(ptotal-btotal, pnum);
		//조회수 카운트 증가
		PDAO.incleaseViews(pnum);
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('구매성공! 구매사항은 구매상품보기에서 확인하실 수 있습니다.');");
		script.println("history.go(-2);");
		script.println("</script>");
		script.close();
		response.sendRedirect("home.jsp");
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('디비 에러 발생');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
	}
%>

</body>
</html>