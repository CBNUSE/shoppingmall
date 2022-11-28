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
	int size = 1024*1024*10; //���κ��ν�� ���쇳�ш린
	String file = "";			//��濡��� �� ���쇱�� �대�(�대��� 蹂�寃쎈�� �� ����) �곕━�� ������?
	String originalFile = "";	//�대��� 蹂�寃쎈��湲� �� �ㅼ�� ���� �대�
	String path2 = "";
	//�ㅼ��濡� ���� ��濡������� 怨쇱��
	String test = "C:\\opensource_workspace\\main_web\\Auction_Site\\src\\main\\webapp\\file";
	//C:\Users\parke\OneDrive\바탕 화면\SE\Shopping\src\main\webapp\file
	try{
		MultipartRequest multi = new MultipartRequest(request, test, size,"UTF-8",new DefaultFileRenamePolicy());
		String name = multi.getParameter("name");			//multi媛�泥대� ���쇰�명�� 諛������쇰��
		String content = multi.getParameter("content");
		String category = multi.getParameter("category");
		int price = Integer.parseInt(multi.getParameter("price"));
		String year = multi.getParameter("year");
		String month = multi.getParameter("month");
		String day = multi.getParameter("day");
		
		String due_date = year+month+day;
	    String date = "";
	        
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
		//Date濡� 蹂�寃쏀��湲� ���� ��吏� ������ yyyy-mm-dd濡� 蹂�寃�
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date tempDate = null;
			    
		//yyyymmdd濡��� ��吏� �����쇰� java.util.Date媛�泥대�� 留���
		tempDate = beforeFormat.parse(due_date);
		out.print(tempDate);	   
		//Date瑜� yyyy-mm-dd �����쇰� 蹂�寃쏀���� String�쇰� 諛���
		date = afterFormat.format(tempDate);
	    
	    out.println("date: " + date);
		
	    //String �� java.sql.Date 蹂��� 
	    String day2 = date; // ������ 吏�耳��� ��
	    java.sql.Date d = java.sql.Date.valueOf(day2);
	    PrDAO PDAO = new PrDAO();
	    
	    Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();	//���� �대��� 諛����� �ㅽ�몃��쇰� ����
		file = multi.getFilesystemName(str);	//��濡��� �� ���� �대� 媛��몄��
		//out.println(file);
		String user_id = (String)session.getAttribute("user_id");
		
		int result = PDAO.write(name, category, price, content, d, file, user_id);
	    //int i = PDAO.insert(name, content, category, price, d, file);	//sql���� date蹂��� �� db�곕��
	    
	    //�ㅻ���
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