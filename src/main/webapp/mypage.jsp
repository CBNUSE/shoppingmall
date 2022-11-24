<%@page import="user.*"%>
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
	UserBean ubean = new UserDAO().retrieveCustomer(cid);	
%>
<body>
	<div><p style="font-size:20px; text-align:center;"><a href="home.jsp">squid</a></p></div>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>My Page</h3>
			</div>
			<div>
				<p>
					<label>이름</label> 
					<input class="w3-input" type="text" id="id" name="id" value="<%=ubean.getCname() %>"> 
				</p>
				
				<br />
				<form action="mypageProc.jsp">	
					<p>
					<label>주소</label> 
					<input class="w3-input" type="text" id="caddress" name="caddress" value="<%=ubean.getCaddress() %>" required> 
					</p>
					<p>
					<label>전화번호</label> 
					<input class="w3-input" type="text" id="ctel" name="ctel" value="<%=ubean.getCtel() %>" required> 
					</p>
					<p class="w3-center">
						<button type="submit" id="joinBtn" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">회원정보 변경</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</body>
</html>