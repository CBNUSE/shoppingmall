<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Shopping mall-manager menu</title>
<link rel="stylesheet" href="css/select_style.css">
</head>
<body>

<div class="container">
     <ul id="menu">
           <!-- open-menu id for click event -->
            <a class="menu-button icon-plus" id="open-menu" href="#menu" title="Show navigation"></a>
            <a class="menu-button icon-minus" href="#0" title="Hide navigation"></a>
       <!--user management-->
            <li class="menu-item">
                <a href="#menu">
                    <span class="fa-solid fa-users-gear"></span>
                </a>
            </li>
       <!--homepage-->
            <li class="menu-item">
                <a href="home.jsp">
                    <span class="fa-brands fa-linkedin-in"></span>
                </a>
            </li>
       <!--order-->
            <li class="menu-item">
                <a href="#none" onclick="orderPage()">
                <script type="text/javascript">
	                function orderPage() {
	                    window.open(
	                      "order.jsp",
	                      "OrderPage",
	                      "width=1400, height=600, top=50, left=50"
	                    );
	                  }
                </script>
                    <span class="fa-solid fa-cart-plus"></span>
                </a>
            </li>
       <!--logout-->
            <li class="menu-item">
            <!-- 구현은 아직... -->
                <a href="#menu">
                    <span class="fa-solid fa-right-from-bracket"></span>
                    
                </a>
            </li>
        </ul>
  
  <div class="content">
    <div class="text">
      <h3> Click circle!</h3>
      <h6>Manager menu</h6>
    </div>
  </div>
</div>
<!-- font awesome -->
<script src="https://use.fontawesome.com/releases/v6.2.1/js/all.js"></script>
<script src="https://use.fontawesome.com/releases/v6.2.0/js/all.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.1/js/all.js"></script>

<!-- js file -->
<script src="js/select.js"></script>
</body>
</html>