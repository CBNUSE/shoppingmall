<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Order page</title>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

	<!-- font -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" rel="stylesheet">
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	
	<script src="js/order.js"></script>
	<link rel="stylesheet" href="css/order_style.css">
</head>
<!--angular module 사용-->
<body ng-app="orderSys" ng-controller="orderSheetController" style="font-size:14px">
	<div class="alert">
		<h2><span class="font">주문서 작성</span> 페이지입니다.</h2>
	<hr/>
	</div>
    
	<div class="content">
        <div class="col-md-12">
        <form action="orderProc.jsp" method="post" enctype="multipart/form-data">
            <table class="table table-striped table-hover table-responsive">
                <thead>
                    <tr>
                        <th>상품 목록 번호</th>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>단가(원)</th>
                        <th>납품업체코드</th>
                        <th>주문날짜</th>
                        <th>cancel</th>
                    </tr>
                </thead>
                <tbody>
                  <!--db에 있는 주문서 출력-->
                    <tr ng-repeat="content in contents">
                        <th>
                            <input type="number" ng-model="content.plistnum" class="form-control bfh-number" placeholder="ProdList Num">
                        </th>
                        <td>
                            <input type="text" ng-model="content.pname" class="form-control" placeholder="product name">
                        </td>
                        <td>
                            <input type="number" ng-model="content.count" class="form-control bfh-number" min=0 placeholder="수량">
                        </td>
                        <td>
                            <input type="number" ng-model="content.osprice" class="form-control bfh-number" placeholder="단가">
                        </td>
                        <td>
                            <input type="number" ng-model="content.sid " class="form-control bfh-number" placeholder="공급 업체">
                        </td>
                        <td>
                        <div class="form-control">
    					<!--날짜 출력-->
	    					<script type="text/javascript">
		    					const date = new Date();
		    					const year = date.getFullYear();
		    					const month = ('0' + (date.getMonth() + 1)).slice(-2);
		    					const day = ('0' + date.getDate()).slice(-2);
		    					const dateStr = year + '-' + month + '-' + day;
	    						document.write(dateStr);
	    					</script>
    					</div>
                        </td>
                 
                        <td>
                            <a href ng:click="deleteSheet($index)"><i class="fa-solid fa-xmark"></i></a>
                        </td>
                    </tr>
                  
                    <tr class="success">
                        <th class="success">TOTAL</th>
                        <td class="success"></td>
                        <td class="success"></td>
                        <td class="success" id="total">{{total()}}(원)</td>
                        <td class="success"></td>
                        <td class="success"></td>
                        <td class="success"></td>
                        <td class="success"></td>
                    </tr>
                </tbody>
            </table>
          <div class="side-btn">
            <a href ng:click="addSheet()" class="btn btn-primary">Add Sheet <i class="fa fa-solid fa-plus"></i></a>
           	<!--주문서 저장--> 
          	<a href=# class="btn btn-primary left-btn" onClick='window.close()'>Place an Order <i class="fa fa-solid fa-paper-plane"></i></a>
          </div>
          <a href type="submit" class="btn btn-primary"style="margin-top:5px">Save</a>
        </div>
    </form>
    </div>
    
</body>
</html>