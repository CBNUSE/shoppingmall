<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="product.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Insert title here</title>
<style>
	.mode{
		margin-left:30px;
	}
</style>
</head>
<%@include file="productDeleteProc.jsp"%>
<body>
	<%
  		String id = null;
		String sort = null;
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		sort = request.getParameter("sort");
		int num = 0;
  		if(session.getAttribute("user_id") != null){
  			id = (String) session.getAttribute("user_id");
  		}
  		if(category != null){
  			num = 1;
  		}
  		if(sort != null){
  			//num = 2;
  			if(sort.equals("popularity")){
  				num = 4;
  				if(category.equals("null")){
  	  				num = 5;
  	  			}
  			}	
  			if(sort.equals("latest")){
  				num = 2;
  				if(category.equals("null")){
  	  				num = 6;
  	  			}
  			}	
  		}
  		if(search != null){
  			num=3;
  		}
  		
  		int pageNumber = 1;
  	%>
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
      <!--카테고리-->
      <hr/>
      <ul class="nav justify-content-center">
      	<li class="nav-item">
          <a class="nav-link active text-body border-end border-start" aria-current="page" href="home.jsp">전체</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active text-body border-end border-start" aria-current="page" href="home.jsp?category=digitaldevice">디지털기기</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-body border-end " href="home.jsp?category=household">생활가전</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-body border-end " href="home.jsp?category=food">식품</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-body border-end " href="home.jsp?category=babyproducts">유아용품</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-body border-end " href="home.jsp?category=furniture">가구</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-body border-end " href="home.jsp?category=game/hobby">게임/취미</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-body border-end " href="home.jsp?category=beauty">뷰티</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-body border-end " href="home.jsp?category=sports">스포츠</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-body border-end " href="home.jsp?category=books">책</a>
          </li>
      </ul>
      <hr/>
      <div class="mode">
      		<h2>you are Buyer!!!</h2>
      </div>
       <div calss="container-fluid " >
        <div class="row">
          <div class="col-10">
          </div>
          <div class="col-2">
            <div class="btn-group btn-group-sm mx-5 " role="group" aria-label="Basic example" >
           	  <%
              	String cat2 = null;
              	if (category != null){
              		cat2 = category;
              	}
              %>
              <a href="home.jsp?sort=popularity&category=<%=cat2 %>"><button type="submit" class="btn btn-dark">Popularity</button></a>
              <%
              	String cat = null;
              	if (category != null){
              		cat = category;
              	}
              %>
              <a href="home.jsp?sort=latest&category=<%=cat %>"><button type="submit" class="btn btn-dark">Latest</button></a>
            </div>
          </div>
        </div>
      </div>
      <!--물품 표시-->
      <div calss="container"></div>
      <div class="row mx-3">
          <div class="col-md-4 mt-4">
          <table style="margin-left:70px;">
                <%
                	PrDAO prDAO = new PrDAO();      	
                	
                	int j=0;
                	
                	if(num == 0){
                		ArrayList<Bean> list = prDAO.getList2();

            			for(int i=0; i<list.size(); i++){
            				if(list.get(i).getBbsavailable()==0){
            					continue;
            				}
            				String address = list.get(i).getAddress();
            				//4열로 나눔(한 행에 4개씩 보여줌)
            				if(j%4 == 0){
            		%>
            			<tr height="220">
            		<%
            			} 
            		%>
            		<td>	
		                <div class="card-body" style="width:250px; height:400px; margin-left:70px;">
		                  <a href="productPage.jsp?id=<%=list.get(i).getId() %>"><img src="file/<%=address %>" style="height:200px; width:230px;" class="card-img-top" alt="..."></a>
		                  <h5 class="card-title overflow-hidden" style="height:30px; width:18rem; font-weight: bold; color:black; text-shadow: 3px 3px 2px silver; font-size: 25px;"><%= list.get(i).getName() %></h5>
		                  <p class="card-text overflow-hidden" style="height:25px; width:18rem; color:red; font-weight: bold; font-size: 20px;"><%= list.get(i).getPrice() %><span style="color:black; font-size:13px;">원</span></p>
		                  <p class="card-text overflow-hidden" style="height:13px; width:18rem; color:black; font-weight: 500; font-size:10px;">duedate : <%= list.get(i).getBbsdate().substring(0,11)+list.get(i).getBbsdate().substring(11,13) + ":" + list.get(i).getBbsdate().substring(14,16)+"" %></p>
		                  <a href="#" class="btn btn-secondary">Auction</a>
		                </div>
	                <!-- <div class="card" style="width: 18rem; margin:20px;">
    	                	<img class="card-img-top" height="250px" alt="..." src="file/">
    	                	
    	                	<a href="#" class="btn btn-secondary">Auction</a>
    	               	</div> -->
                   	</td>
            		<%	
            				j += 1;//j값을 증가하여 하나의 행에 총3개의 차량정보를 보여주기 위해서 증가
            			}
                	}
            		%>
            	</table>
            		<% 	
                	if (num == 1){
                		Vector<Bean> vec = prDAO.categorySelectProduct(category);
               		%>
                	<table style="margin-left:70px;">	
               		<%
                		int r = 0;
                		
                		for(int i=0; i<vec.size(); i++){	
          					Bean bean = vec.get(i); //벡터에 담긴 빈클래스를 하나씩 추출
          					if(bean.getBbsavailable()==0){
            					continue;
            				}
          					String address = bean.getAddress();
          					if(r%4 == 0){
        			%>
         	                <tr class="box2">
                    <%
          					}
                   	%>
                   	<td>
    	               	<div class="card-body" style="width:250px; height:400px; margin-left:70px;">
		                  <a href="productPage.jsp?id=<%=bean.getId() %>"><img src="file/<%=address %>" style="height:200px; width:230px;"  class="card-img-top" alt="..."></a>
		                  <h5 class="card-title overflow-hidden" style="height:30px; width:18rem; font-weight: bold; color:black; text-shadow: 3px 3px 2px silver; font-size: 25px;"><%= bean.getName() %></h5>
		                  <p class="card-text overflow-hidden" style="height:25px; width:18rem; color:red; font-weight: bold; font-size: 20px;"><%= bean.getPrice() %><span style="color:black; font-size:13px;">원</span></p>
		                  <p class="card-text overflow-hidden" style="height:13px; width:18rem; color:black; font-weight: 500; font-size:10px;">duedate : <%= bean.getBbsdate().substring(0,11)+bean.getBbsdate().substring(11,13) + ":" + bean.getBbsdate().substring(14,16)+"" %><br></p>
		                  <a href="#" class="btn btn-secondary">Auction</a>
		                </div>           	
                   	</td>
              		<%
          					r+=1;			
       					}
               		%>
               		</table>
               		<%
                	}
      				%>
          	</table>
          	<% 	
               	if (num == 2){
               		Vector<Bean> vec = prDAO.sortLatest(category);
            %>
            <table style="margin-left:70px;">
            <%
            		int x=0;
               		for(int i=0; i<vec.size(); i++){
               			Bean bean = vec.get(i);
               			if(bean.getBbsavailable()==0){
        					continue;
        				}
               			String address = bean.getAddress();
               			if(x%4 == 0){
            %>
            	<tr class="box2">
            <%
               			}
            %>
            <td>
            	<div class="card-body" style="width:250px; height:400px; margin-left:70px;">
            		<a href="productPage.jsp?id=<%=bean.getId() %>"><img src="file/<%=address %>" style="height:200px; width:230px;"  class="card-img-top" alt="..."></a>
               		<h5 class="card-title overflow-hidden" style="height:30px; width:18rem; font-weight: bold; color:black; text-shadow: 3px 3px 2px silver; font-size: 25px;"><%= bean.getName() %></h5>
               		<p class="card-text overflow-hidden" style="height:25px; width:18rem; color:red; font-weight: bold; font-size: 20px;"><%= bean.getPrice() %><span style="color:black; font-size:13px;">원</span></p>
               		<p class="card-text overflow-hidden" style="height:13px; width:18rem; color:black; font-weight: 500; font-size:10px;">duedate : <%= bean.getBbsdate().substring(0,11)+bean.getBbsdate().substring(11,13) + ":" + bean.getBbsdate().substring(14,16)+"" %><br></p>
               		<a href="#" class="btn btn-secondary">Auction</a>
             	</div>           	
            </td>            
            <%
            		x+=1;
               		}
            %>
            </table>
            <%
               	}
            %>
            <% 	
               	if (num == 3){
               		Vector<Bean> vec = prDAO.search(search);
            %>
            <table style="margin-left:70px;">
            <%
            		int z=0;
               		for(int i=0; i<vec.size(); i++){
               			Bean bean = vec.get(i);
               			if(bean.getBbsavailable()==0){
        					continue;
        				}
               			String address = bean.getAddress();
               			if(z%4 == 0){
            %>
            	<tr class="box2">
            <%
               			}
            %>
            <td>
            	<div class="card-body" style="width:250px; height:400px; margin-left:70px;">
            		<a href="productPage.jsp?id=<%=bean.getId() %>"><img src="file/<%=address %>" style="height:200px; width:230px;"  class="card-img-top" alt="..."></a>
               		<h5 class="card-title overflow-hidden" style="height:30px; width:18rem; font-weight: bold; color:black; text-shadow: 3px 3px 2px silver; font-size: 25px;"><%= bean.getName() %></h5>
               		<p class="card-text overflow-hidden" style="height:25px; width:18rem; color:red; font-weight: bold; font-size: 20px;"><%= bean.getPrice() %><span style="color:black; font-size:13px;">원</span></p>
               		<p class="card-text overflow-hidden" style="height:13px; width:18rem; color:black; font-weight: 500; font-size:10px;">duedate : <%= bean.getBbsdate().substring(0,11)+bean.getBbsdate().substring(11,13) + ":" + bean.getBbsdate().substring(14,16)+"" %><br></p>
               		<a href="#" class="btn btn-secondary">Auction</a>
             	</div>           	
            </td>
            <%
            		z+=1;
               		}
            %>
            </table>
            <%
               	}
            %>
            <% 	
               	if (num == 4){
               		Vector<Bean> vec = prDAO.sortPopularity(category);
            %>
            <table style="margin-left:70px;">
            <%
            		int y=0;
               		for(int i=0; i<vec.size(); i++){
               			Bean bean = vec.get(i);
               			if(bean.getBbsavailable()==0){
        					continue;
        				}
               			String address = bean.getAddress();
               			if(y%4 == 0){
            %>
            	<tr class="box2">
            <%
               			}
            %>
            <td>
            	<div class="card-body" style="width:250px; height:400px; margin-left:70px;">
            		<a href="productPage.jsp?id=<%=bean.getId() %>"><img src="file/<%=address %>" style="height:200px; width:230px;"  class="card-img-top" alt="..."></a>
               		<h5 class="card-title overflow-hidden" style="height:30px; width:18rem; font-weight: bold; color:black; text-shadow: 3px 3px 2px silver; font-size: 25px;"><%= bean.getName() %></h5>
               		<p class="card-text overflow-hidden" style="height:25px; width:18rem; color:red; font-weight: bold; font-size: 20px;"><%= bean.getPrice() %><span style="color:black; font-size:13px;">원</span></p>
               		<p class="card-text overflow-hidden" style="height:13px; width:18rem; color:black; font-weight: 500; font-size:10px;">duedate : <%= bean.getBbsdate().substring(0,11)+bean.getBbsdate().substring(11,13) + ":" + bean.getBbsdate().substring(14,16)+"" %><br></p>
               		<a href="#" class="btn btn-secondary">Auction</a>
             	</div>           	
            </td>
            <%
            		y+=1;
               		}
            %>
            </table>
            <%
               	}
            %>
            <% 	
               	if (num == 5){
               		Vector<Bean> vec = prDAO.sortPopularity2();
            %>
            <table style="margin-left:70px;">
            <%
            		int b=0;
               		for(int i=0; i<vec.size(); i++){
               			Bean bean = vec.get(i);
               			if(bean.getBbsavailable()==0){
        					continue;
        				}
               			String address = bean.getAddress();
               			if(b%4 == 0){
            %>
            	<tr class="box2">
            <%
               			}
            %>
            <td>
            	<div class="card-body" style="width:250px; height:400px; margin-left:70px;">
            		<a href="productPage.jsp?id=<%=bean.getId() %>"><img src="file/<%=address %>" style="height:200px; width:230px;"  class="card-img-top" alt="..."></a>
               		<h5 class="card-title overflow-hidden" style="height:30px; width:18rem; font-weight: bold; color:black; text-shadow: 3px 3px 2px silver; font-size: 25px;"><%= bean.getName() %></h5>
               		<p class="card-text overflow-hidden" style="height:25px; width:18rem; color:red; font-weight: bold; font-size: 20px;"><%= bean.getPrice() %><span style="color:black; font-size:13px;">원</span></p>
               		<p class="card-text overflow-hidden" style="height:13px; width:18rem; color:black; font-weight: 500; font-size:10px;">duedate : <%= bean.getBbsdate().substring(0,11)+bean.getBbsdate().substring(11,13) + ":" + bean.getBbsdate().substring(14,16)+"" %><br></p>
               		<a href="#" class="btn btn-secondary">Auction</a>
             	</div>           	
            </td>
            <%
            		b+=1;
               		}
            %>
            </table>
            <%
               	}
            %>
            <% 	
               	if (num == 6){
               		Vector<Bean> vec = prDAO.sortLatest2();
            %>
            <table style="margin-left:70px;">
            <%
            		int b=0;
               		for(int i=0; i<vec.size(); i++){
               			Bean bean = vec.get(i);
               			if(bean.getBbsavailable()==0){
        					continue;
        				}
               			String address = bean.getAddress();
               			if(b%4 == 0){
            %>
            	<tr class="box2">
            <%
               			}
            %>
            <td>
            	<div class="card-body" style="width:250px; height:400px; margin-left:70px;">
            		<a href="productPage.jsp?id=<%=bean.getId() %>"><img src="file/<%=address %>" style="height:200px; width:230px;"  class="card-img-top" alt="..."></a>
               		<h5 class="card-title overflow-hidden" style="height:30px; width:18rem; font-weight: bold; color:black; text-shadow: 3px 3px 2px silver; font-size: 25px;"><%= bean.getName() %></h5>
               		<p class="card-text overflow-hidden" style="height:25px; width:18rem; color:red; font-weight: bold; font-size: 20px;"><%= bean.getPrice() %><span style="color:black; font-size:13px;">원</span></p>
               		<p class="card-text overflow-hidden" style="height:13px; width:18rem; color:black; font-weight: 500; font-size:10px;">duedate : <%= bean.getBbsdate().substring(0,11)+bean.getBbsdate().substring(11,13) + ":" + bean.getBbsdate().substring(14,16)+"" %><br></p>
               		<a href="#" class="btn btn-secondary">Auction</a>
             	</div>           	
            </td>
            <%
            		b+=1;
               		}
            %>
            </table>
            <%
               	}
            %>
	      </div>
	    </div>

      <div>
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