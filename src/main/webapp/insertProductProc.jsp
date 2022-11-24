<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="product.*"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	int size = 1024*1024*10; //저장가능한 파일크기
	String file = "";			//업로드 한 파일의 이름(이름이 변경될 수 있음) 우리는 안할듯?
	String originalFile = "";	//이름이 변경되기 전 실제 파일 이름
	String path2 = "";
	//실제로 파일 업로드하는 과정
	String test = "C:\\opensource_workspace\\main_web\\Auction_Site\\src\\main\\webapp\\file";
	
	try{
		MultipartRequest multi = new MultipartRequest(request, test, size,"UTF-8",new DefaultFileRenamePolicy());
		String name = multi.getParameter("name");			//multi객체로 파라미터 받아와야됨
		String content = multi.getParameter("content");
		String category = multi.getParameter("category");
		int price = Integer.parseInt(multi.getParameter("price"));
		String year = multi.getParameter("year");
		String month = multi.getParameter("month");
		String day = multi.getParameter("day");
		
		String due_date = year+month+day;
	    String date = "";
	        
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
		//Date로 변경하기 위해 날짜 형식을 yyyy-mm-dd로 변경
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date tempDate = null;
			    
		//yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만듬
		tempDate = beforeFormat.parse(due_date);
		out.print(tempDate);	   
		//Date를 yyyy-mm-dd 형식으로 변경하여 String으로 반환
		date = afterFormat.format(tempDate);
	    
	    out.println("date: " + date);
		
	    //String → java.sql.Date 변환 
	    String day2 = date; // 형식을 지켜야 함
	    java.sql.Date d = java.sql.Date.valueOf(day2);
	    PrDAO PDAO = new PrDAO();
	    
	    Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();	//파일 이름을 받아와 스트링으로 저장
		file = multi.getFilesystemName(str);	//업로드 된 파일 이름 가져옴
		//out.println(file);
		String user_id = (String)session.getAttribute("user_id");
		
		int result = PDAO.write(name, category, price, content, d, file, user_id);
	    //int i = PDAO.insert(name, content, category, price, d, file);	//sql형식 date변환 후 db연동
	    
	    //오류시
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