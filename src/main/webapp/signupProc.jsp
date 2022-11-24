<%@page import="user.UserBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%
	request.setCharacterEncoding("UTF-8"); //한글처리
%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<body>
<%
	request.setCharacterEncoding("UTF-8"); //한글처리
	
	String cid = request.getParameter("cid");
	String cpassword = request.getParameter("cpassword");
	String password2 = request.getParameter("cpassword2");
	String cname = request.getParameter("cname");
	String ctel = request.getParameter("ctel");
	String caddress = request.getParameter("caddress");
	
	UserDAO userDAO = new UserDAO();
	
	Vector<UserBean> vec = userDAO.allSelectMember();
	
	for(int i=0; i<vec.size(); i++){
		UserBean bean = vec.get(i); //벡터에 담긴 빈클래스를 하나씩 추출
		if(cid.equals(bean.getCid())){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('같은 아이디가 존재합니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return;
		}
	}
	
	//하나라도 입력이 제대로 안되어있다면
	if(cid == "" || cpassword == "" ||  cname == "" || ctel == "" || caddress == ""){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있음');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	//비밀번호의 길이가 짧을 때
	if(cpassword.length()<6){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호의 길이가 너무 짧습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	//비밀번호가 일치하지 않을 때
	if(!(cpassword.equals(password2))){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 일치하지 않습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	//response.sendRedirect("signup.jsp");
%>
<jsp:useBean id="mbean" class="user.UserBean">
	<jsp:setProperty name="mbean" property="*"/>
</jsp:useBean>
<!-- 자바빈에 데이터 담고 보내주기 -->
<%
	userDAO.insertCustomer(mbean);

	//회원가입이 되었다면
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('회원가입성공! 환영합니다');");
	script.println("history.go(-2);");
	script.println("</script>");
	script.close();
	response.sendRedirect("login.jsp");
%>
</body>
</html>