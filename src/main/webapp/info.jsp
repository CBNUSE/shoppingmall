<%@page import="product.Bean"%>
<%@page import="java.util.Vector"%>
<%@page import="product.ProductBean"%>
<%@page import="product.PrDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Squid Auction</title>
 	<%
 		String id = null;
 		if(session.getAttribute("user_id") != null){
			id = (String) session.getAttribute("user_id");
		}
 	%>
</head>
<body>
      <!--네브바-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="home.jsp"> Squid Auction</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="home.jsp">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Account</a>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  Mode
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li><a class="dropdown-item" href="#">Buy</a></li>
                  <li><a class="dropdown-item" href="#">Sell</a></li>
                </ul>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Ask</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Log out</a>
              </li>
            </ul>
            <form class="d-flex">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
        </div>
      </nav>
      
    <hr/>
    <%
    	PrDAO PDAO = new PrDAO();
    	
    	Vector<ProductBean> vec = PDAO.finishallselect();
    	for(int i=0; i<vec.size(); i++){
    		ProductBean pbean = vec.get(i);
    		
    		if(id.equals(pbean.getBuy_id()) || id.equals(pbean.getUser_id())){
    			Bean bean = PDAO.getProduct(pbean.getProduct_id());
    			%>
    			
					<div class="container my-5">
        <div class="row my-5">
            <center><h1>Congratulations</h1></center>      
                <div class="col-md-6">
                    <img src="file/<%=bean.getAddress() %>" class="img-fluid" alt="..." style="width:500px; height:600px;">
                </div>
                <div class="col-md-6">
                  <div class="row my-1">
                    <div class="col">
                      <label for="Title" class="form-label">Title</label>
                      <text class="form-control" id="exampleFormControlTextarea1" rows="1"><%=bean.getName() %></text>
                    </div>
                  </div>
                 
                   <div class="row my-2">
                    <div class="col">
                      <label for="Title" class="form-label">낙찰가</label>
                      <text class="form-control" id="exampleFormControlTextarea1" rows="1"><%=pbean.getPrice() %> $</text>
                  </div>
                 </div>
                 <div class="row my-2">
                    <div class="col">
                      <label for="Title" class="form-label">구매자 아이디</label>
                      <text class="form-control" id="exampleFormControlTextarea1" rows="1"><%=pbean.getBuy_id() %></text>
                  </div>
                 </div>
                 <div class="row my-2">
                    <div class="col">
                      <label for="Title" class="form-label">판매자 아이디</label>
                      <text class="form-control" id="exampleFormControlTextarea1" rows="1"><%=pbean.getUser_id() %></text>
                  </div>
                 </div>
                 <div class="row my-5">
                  <div class="col-md-12">
                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal1">
                      Go Chatt!
                    </button>
                    
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            Did you want chatting?!
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                          <a href="websocketChat.jsp"><button type="button" class="btn btn-secondary">Go Chatt!</button></a>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>
               </div>
                </div>
              </div>
          
          
    			<% 
    		}
    	}
    %>
    <hr/>
    <center><p> Team squid </p></center>
      </div>
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
</body>
</html>