<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="home.jsp"> Squid 쇼핑몰</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="purchaseInfo.jsp">구매상품 보러가기</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="mypage.jsp">마이페이지</a>
              </li>
               <li class="nav-item">
                <a class="nav-link" href="logout.jsp">Logout</a>
              </li>
            </ul>
            
            <form action="home.jsp" method="get" class = "d-flex">
            <input type = "text" name = "search" class="form-control" type ="search" placeholder="search" >
            <button class = "btn btn-outline-success" type = "submit">search</button>
            </form>
          </div>
        </div>
      </nav>
</body>
</html>