<%@page import="buy.*"%>
<%@page import="product.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%
	session = request.getSession();
	String cid = (String)session.getAttribute("user_id");

	int bnum = Integer.parseInt(request.getParameter("bnum"));
	
	BuyBean bbean = new BuyBean();
	BuyDAO BDAO = new BuyDAO();
	
	bbean = BDAO.oneselectBuy(bnum);
	
	PrDAO PDAO = new PrDAO();
	ProductBean pbean = new ProductBean();
	pbean = PDAO.retrieveProduct(bbean.getPnum());
%>
<body>
	<div><p style="font-size:20px; text-align:center;"><a href="home.jsp">squid</a></p></div>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>환불하기</h3>
			</div>
			<div>
				<br />
				<form action="returnProc.jsp">	
					<p style="display:none">
						<label>구매번호</label> 
						<input class="w3-input" type="text" id="bnum" name="bnum" value="<%=bbean.getBnum() %>"> 
					</p>
					<p>
					<label>상품명</label> 
					<input class="w3-input" type="text" id="pname" name="pname" value="<%=pbean.getPname() %>" required> 
					</p>
					<p>
					<label>구매가</label> 
					<input class="w3-input" type="text" id="bprice" name="bprice" value="<%=bbean.getBprice() %>" required> 
					</p>
					<p>
					<label>구매수량</label> 
					<input class="w3-input" type="text" id="btotal" name="btotal" value="<%=bbean.getBtotal() %>" required> 
					</p>
					<p>
					<label>환불사유</label> 
					<input class="w3-input" id="reason" name="reason" required> 
					</p>
					<p class="w3-center">
						<button type="submit" id="joinBtn" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">환불신청하기</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</body>
</html>