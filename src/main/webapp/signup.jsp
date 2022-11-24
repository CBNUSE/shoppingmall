<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<%
	request.setCharacterEncoding("UTF-8"); //한글처리
%>
<body>
	 <!--네브바-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="#"> Squid Auction</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
      </nav>
     
    <hr/>
    <div class="container my-5">
        <div class="row my-5">
            <form action="signupProc.jsp">
            	<div class="mb-3">
                    <label for="" class="form-label">아이디</label>
                    <input type="text" class="form-control" name="cid">
                </div>
                <div class="mt-3 mb-3">
                  <label for="exampleInputPassword1" class="form-label">비밀번호</label>
                  <input type="password" class="form-control" name="cpassword">
                </div>
                <div class="mt-3 mb-3">
                  <label for="exampleInputPassword1" class="form-label">비밀번호확인</label>
                  <input type="password" class="form-control" name="cpassword2">
                </div>
                <div class="mb-3">
                    <label class="form-label">이름</label>
                    <input type="text" class="form-control" name="cname">
                </div>
                <div class="mb-3">
                    <label for="inputPhonenumber" class="form-label">전화번호</label>
                    <input type="text" class="form-control" name="ctel" >
                </div>        
                <div class="mb-3">
                    <label class="form-label">주소</label>
                    <input type="text" class="form-control" name="caddress">
                </div>
                <button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModa1">
                  회원가입하기
                </button>
                <a href="login.jsp"><button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModa2">
                  취소
                </button></a>
              </form>
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