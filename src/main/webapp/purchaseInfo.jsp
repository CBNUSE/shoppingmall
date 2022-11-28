<%@page import="buy.*"%>
<%@page import="product.*"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Squid 쇼핑몰</title>
 	<%
 		request.setCharacterEncoding("UTF-8");
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
          <a class="navbar-brand" href="home.jsp"> Squid 쇼핑몰</a>
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
    	BuyDAO BDAO = new BuyDAO();
    	int num = 0;
    	Vector<BuyBean> vec = BDAO.retrieveBuy(id);
    	for(int i=0; i<vec.size(); i++){
    		BuyBean bbean = vec.get(i);
    		
    		if(bbean.getBtag() == 1)continue;	//환불신청 상품 표시X
    		
    		if(bbean != null){
    			num = bbean.getBnum();
    			ProductBean pbean = PDAO.retrieveProduct(bbean.getPnum());
    			%>
    			
					<div class="container my-5">
        <div class="row my-5">
            <center><h1>빠른 시일 내에 배송해드리겠습니다!</h1></center>      
                <div class="col-md-6">
                    <img src="file/<%=pbean.getPaddress() %>" class="img-fluid" alt="..." style="width:400px; height:400px;">
                </div>
                <div class="col-md-6">
                  <div class="row my-1">
                    <div class="col">
                      <label for="Title" class="form-label">상품명</label>
                      <text class="form-control" id="exampleFormControlTextarea1" rows="1"><%=pbean.getPname() %></text>
                    </div>
                  </div>
                 
                   <div class="row my-2">
                    <div class="col">
                      <label for="Title" class="form-label">구매가격</label>
                      <text class="form-control" id="exampleFormControlTextarea1" rows="1"><%=bbean.getBprice() %> $</text>
                  </div>
                 </div>
                 <div class="row my-2">
                    <div class="col">
                      <label for="Title" class="form-label">구매수량</label>
                      <text class="form-control" id="exampleFormControlTextarea1" rows="1"><%=bbean.getBtotal() %></text>
                  </div>
                 </div>
                 <div class="row my-2">
                    <div class="col">
                      <label for="Title" class="form-label">구매 날짜</label>
                      <text class="form-control" id="exampleFormControlTextarea1" rows="1"><%=bbean.getBday() %></text>
                  </div>
                 </div>
                 <div class="row my-5">
                  <div class="col-md-12">
                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal1">
                      판매자와 상담하러가기
                    </button>
                    <a href="return.jsp?bnum=<%=num %>"><button type="button" class="btn btn-secondary" style="margin-left:10px;">환불하기</button></a>

                    </form>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            판매자와 상담을 원하시나요?
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소하기</button>
                          <a href="websocketChat.jsp"><button type="button" class="btn btn-secondary">네</button></a>
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