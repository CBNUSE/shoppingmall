<%@page import="java.io.PrintWriter"%>
<%@page import="user.UserBean"%>
<%@page import="java.util.Vector"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("euc-kr");	

	UserDAO userDAO = new UserDAO();
	
	Vector<UserBean> vec = userDAO.allSelectMember();
	String id = request.getParameter("id");
	String idcheck = null;
	String password = request.getParameter("password");
	
	for(int i=0; i<vec.size(); i++){
			UserBean bean = vec.get(i); //���Ϳ� ��� ��Ŭ������ �ϳ��� ����
			if(id.equals(bean.getId())){
				idcheck = bean.getId();
			}
	}
	
	if(idcheck == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�ش� ���̵� �������� �ʽ��ϴ�');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;	
	}
	
	if(!(password.equals(userDAO.getPass(idcheck)))){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	//���̵�� �н����带 �޾Ƽ� ���� �����غ���
	session.setAttribute("user_id", id);
	//������ �����ð� ����
	session.setMaxInactiveInterval(60*60); //60��*2������
	
	response.sendRedirect("select.jsp");
%>
<body>

</body>
</html>