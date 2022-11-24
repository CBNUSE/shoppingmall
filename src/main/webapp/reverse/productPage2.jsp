<%@page import="bid.bid_bean"%>
<%@page import="bid.BidDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="product.*"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.net.URLEncoder" %>
<%@page import="java.util.*" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.text.SimpleDateFormat" %>

<!doctype html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Squid Auction</title>
    
    <%	
    	request.setCharacterEncoding("euc-kr");
    
		int id = 10;
		if(request.getParameter("id") != null){
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		Bean pro = new PrDAO().getPro2(id);	
 	 %>
  </head>
  
      <!--³×ºê¹Ù-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="home2.jsp"> Squid Auction</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="home2.jsp">Home</a>
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
                <a class="nav-link" href="logout.jsp">Logout</a>
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
    <div class="container my-5">
   
        <div class="row my-5">
          
                <div class="mb-3">
                    <label for="Title" class="form-label">Title</label>
                    <p type="text" class="form-control"><%= pro.getName() %></p>
				</div>
                <div class="mb-3">
                    <label for="Title" class="form-label">Image</label>
					</br>
                    <img src="../file/<%=pro.getAddress() %>" class="img-fluid" alt="..." width="500px" height="700px">
                </div>
      
                <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">Product description</label>
                    <textarea class="form-control" disabled id="exampleFormControlTextarea1" rows="5"><%= pro.getContent() %></textarea>
                  </div>
                </div>
                <div class=row>
                
                <div class="col-md-4">               
	                <label for="inputZip" class="form-label">Start Price</label>
	                <p type="text" class="form-control"><%= pro.getPrice() %>$</p>
                </div>
              <%
              	BidDAO BDAO = new BidDAO();
            	bid_bean bbean = BDAO.getLowest(id);
              %>
                <div class="col-md-4">
                 <label for="inputZip" class="form-label">Lowest Bidding Price</label>
                 <p type="text" class="form-control"><%=bbean.getPrice() %>$</p>
                </div>
                
                <div class="col-md-4">
                 <label for="inputZip" class="form-label">Lowest Bidder</label>
                 <p type="text" class="form-control"><%=bbean.getBuy_id() %></p>
                </div>
               
				
                <div class="row g-3">
                
                </div>
                
        </div>
    </div>
    <div class="container ">
    <div class="row g-3 ">
        <div class="col-md-4">
        
        
          <label for="inputyear" class="form-label ">limit time</label>
         
         <div class="container " style="width:500px;">
         <div class="row g-3 ">
		<%
			SimpleDateFormat dfhm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Calendar cal = Calendar.getInstance();
		
        	int yyyy = cal.get(Calendar.YEAR);
        	int MM = cal.get(Calendar.MONTH);
        	int dd = cal.get(Calendar.DATE);
        	int hh = cal.get(Calendar.HOUR);
        	int mm = cal.get(Calendar.MINUTE);
        	int ss = cal.get(Calendar.SECOND);
        	
        	cal.set(yyyy,MM,dd,hh,mm,ss);
        	
        	Date currentTime = new Date();
        	
        	String duedate = pro.getBbsdate();
        	String today = dfhm.format(currentTime);
        	
        	Date endDate = null;
        	Date now = null;
        	
        	long diff = 0;
        	long diffss= 0;
        	long diffdd = 0;
        	long diffhh = 0;
        	long diffmm = 0;
        	long diffsc = 0;
        	
        	endDate = dfhm.parse(duedate);
        	now = dfhm.parse(today);
        	
        	diff = endDate.getTime() - now.getTime();
        	diffss = diff / 1000;
            diffdd = diffss / (24*60*60);
            diffhh = (diffss % (24*60*60)) / (60*60);
            diffmm = ((diffss % (24*60*60)) % (60*60)) / 60;
            diffsc =  ((diffss % (24*60*60)) % (60*60)) % 60;


            out.println("Remaining time : " + diffdd + "days " + diffhh + "hour " + diffmm + "minute " + diffsc + "second <br/>");
        	%>           
		</div>
        </div>
        <div class="col-md-4">
            <label class="p-2">   </label>
          
        </div>

    </div>

    <div class="row">
        <div class="col-12">
          <!-- Button trigger modal -->
<form action="productBidProc2.jsp">
  <div class="col-md-4" style="margin-bottom:15px;">           
     <label for="inputyear" class="form-label ">Bidding</label>
     <input type="text" class="form-control"  name="Biddingprice">
     <input type="hidden" name="product_id" value="<%=id %>">
     <button type="submit" class="btn btn-secondary">Bid</button>
     <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModa2">
  Cancel
</button>
  </div>
  
</form>



<!-- Modal -->
<div class="modal fade" id="exampleModa2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Do you want to cancel?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <a href="#"><button type="button" class="btn btn-secondary">Cancel</button></a>
      </div>
    </div>
  </div>
</div>
        </div>
  

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