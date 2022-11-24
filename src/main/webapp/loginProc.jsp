<%@page import="java.io.PrintWriter"%>
<%@page import="user.UserBean"%>
<%@page import="java.util.Vector"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("UTF-8");	

	UserDAO userDAO = new UserDAO();
	
	Vector<UserBean> vec = userDAO.allSelectMember();
	
	String mid = "park";		//관리자 계정
	
	String id = request.getParameter("id");
	String idcheck = null;
	String password = request.getParameter("password");
	
	for(int i=0; i<vec.size(); i++){
			UserBean bean = vec.get(i); //벡터에 담긴 빈클래스를 하나씩 추출
			if(id.equals(bean.getCid())){
				idcheck = bean.getCid();
			}
	}
	
	if(idcheck == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('해당 아이디가 존재하지 않습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;	
	}
	
	if(!(password.equals(userDAO.getPass(idcheck)))){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 일치하지 않습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	//아이디와 패스워드를 받아서 세션 저장해보자
	session.setAttribute("user_id", id);
	//세션의 유지시간 설정
	session.setMaxInactiveInterval(60*60); //60초*2간유지
	
	//관리자 계정일 시
	if(mid.equals(idcheck)){
		response.sendRedirect("select.jsp");
	}
	else{
		response.sendRedirect("home.jsp");
	}
%>
<body>

</body>
</html>