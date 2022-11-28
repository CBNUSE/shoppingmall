<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Shopping mall</title>
 <link rel="stylesheet" href="css/login_style.css">
</head>
<body>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="signupProc.jsp">
			<h1>Create Account</h1>
			<input type="id" name="cid" placeholder="ID" />
			<input type="password" name="cpassword" placeholder="Password" />
			<input type="password" name="cpassword2" placeholder="Confirm Password" />
			<input type="text" name="cname" placeholder="Name" />
			<input type="tel" name="ctel" placeholder="Tel" />
			<input type="text" name="caddress" placeholder="Address" />
			
			<button type="submit">Sign Up</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="loginProc.jsp">
			<h1>Sign in</h1>
			<input type="text" name="id" placeholder="ID" />
			<input type="password" name="password" placeholder="Password" />
			<a href="#">Forgot your password?</a>
			<button type="submit">Sign In</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello!</h1>
				<p>Enter your personal details and start buying at our shopping mall!</p>
				<button class="ghost" id="signUp">Sign Up</button>
			</div>
		</div>
	</div>
</div>
<!-- js file -->
<script src="js/sign.js"></script>
</body>
</html>