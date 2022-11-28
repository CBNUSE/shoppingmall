<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.6.2/css/bootstrap-slider.min.css" integrity="sha256-G3IAYJYIQvZgPksNQDbjvxd/Ca1SfCDFwu2s2lt0oGo=" crossorigin="anonymous" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
	.alert{
		margin-left:30px;
		font-family: cursive;
	}
	.font{
		color : hotpink;
	}
</style>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	
	String id = (String)session.getAttribute("user_id");
	//out.println(id);
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="home.jsp"> Squid Auction</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">Home</a>
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
                <a class="nav-link" href="logout.jsp">Log out</a>
              </li>
            </ul>
            <form class="d-flex">
              <input class="form-control me-2 " type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
        </div>
      </nav>
      
    <hr/>
    
<div class="alert">
	<h2>Order product by <span class="font"> Order system</span></h2>
</div>
<form action="insertProductProc.jsp" method="post" enctype="multipart/form-data" >
<div class="container my-5">
        <div class="row my-5">
			<div class="mb-3">
                <label for="Title" class="form-label">Title</label>
                <input type="text" class="form-control" id="name" name="name">
            </div>
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">Product description</label>
                <textarea class="form-control" name="content" rows="5"></textarea>
            </div>
            <div class=row>
            <div class="col-md-4">
                  <label for="inputZip" class="form-label">Starting price</label>
                  <input type="number" class="form-control" name="price"  min="0" step="1000" placeholder="가격을 입력해주세요. (￦)">
            </div>
            <div class="col-md-4">
               <label for="inputyear" class="form-label ">Category</label>
               <select name="category" class="form-select">
                 <option selected>digitaldevice</option>
                 <option value="beauty">beauty</option>
                 <option value="household">household</option>
                 <option value="food">food</option>
                 <option value="babyproducts">babyproducts</option>
                 <option value="furniture">furniture</option>
                 <option value="game/hobby">game/hobby</option>
                 <option value="sports">sports</option>
                 <option value="books">books</option>
               </select>
            </div>
            </div>
            
            <div class="col-md-4">
        
        <label class="">  Due date </label>
          <label for="inputyear" class="form-label "></label>
          <select name="year" class="form-select">
            <option selected>Year</option>
            <option>2021</option>
            <option>2022</option>
            <option>2023</option>
            <option>2024</option>
            <option>2025</option>
          </select>
        </div>
        <div class="col-md-4">
            <label class="p-2">   </label>
          <label for="inputState" class="form-label"></label>
          <select name="month" class="form-select">
            <option selected>Month</option>
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
            <option>07</option>
            <option>08</option>
            <option>09</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
          </select>
        </div>
        <div class="col-md-4">
          <label for="inputZip" class="form-label"></label>
          <input type="text" class="form-control" name="day" placeholder="Day">
        </div>
            
            <div class="col-md-4">
              
            <div class="mt-3 mb-3">
            	<label for="formFileMultiple" class="form-label">File upload</label>
		    	<input class="form-control" type="file" name="file"><br><br>	
    		</div>
    		
    		<button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal1">
  				Registration
			</button>	
			<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModa2">
			  Cancel
			</button>
    	</div>
    	</div>
</div>
</form>
<script>
        $(document).ready(function(){
            var fileTarget = $('.file-box .upload-hidden');
            fileTarget.on('change', function(){
                if(window.FileReader){
                    var filename = $(this)[0].files[0].name;
                } else {
                    var filename = $(this).val().split('/').pop().split('\\').pop();
                }
                $(this).siblings('.upload-name').val(filename);
            });
        });
</script>
</body>
</html>