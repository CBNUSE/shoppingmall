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
         <a class="navbar-brand" href="home.jsp"> Squid Auction</a>
         <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
           <span class="navbar-toggler-icon"></span>
         </button>
         <div class="collapse navbar-collapse" id="navbarSupportedContent">
           <ul class="navbar-nav me-auto mb-2 mb-lg-0">
             <li class="nav-item">
               <a class="nav-link" href="info.jsp">winning bid Product</a>
             </li>
             <li class="nav-item dropdown">
               <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                 Mode
               </a>
               <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                 <li><a class="dropdown-item" href="home.jsp">buyer</a></li>
                 <li><a class="dropdown-item" href="reverse/home2.jsp">seller</a></li>
               </ul>
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