<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="product.*"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	int size = 1024*1024*10; //저장가능한 파일크기
	String file = "";			//업로드 파일이름
	String originalFile = "";	//업로드 전 설정 파일 이름
	String path2 = "";
	
	//경로주소
	String test = "C:\\Users\\parke\\OneDrive\\바탕 화면\\SE\\Shopping\\src\\main\\webapp\\file";

	try{
		MultipartRequest multi = new MultipartRequest(request, test, size,"UTF-8",new DefaultFileRenamePolicy());
		String name = multi.getParameter("name");			
		String content = multi.getParameter("content");
		String category = multi.getParameter("category");
		int price = Integer.parseInt(multi.getParameter("price"));
		int total = Integer.parseInt(multi.getParameter("total"));
	    PrDAO PDAO = new PrDAO();
	    
	    Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();	
		file = multi.getFilesystemName(str);	
		
		java.util.Date utilDate = new java.util.Date(); //현재 날짜(자바 객체)
		java.sql.Date date = new java.sql.Date(utilDate.getTime()); //sql date객체로 변환
		
		int result = PDAO.insertProduct(name, category, price, content, date, file, total);
	    
	    //등록 실패 시
	    if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('error')");
			script.println("history.back()");
			script.println("</script>");	
		}
		
		else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'home.jsp'");
			script.println("</script>");
		}
	} catch (Exception e){
		e.printStackTrace();
	}
%>
</body>
</html>