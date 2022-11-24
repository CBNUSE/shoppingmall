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
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	int size = 1024*1024*10; //���尡���� ����ũ��
	String file = "";			//���ε� �� ������ �̸�(�̸��� ����� �� ����) �츮�� ���ҵ�?
	String originalFile = "";	//�̸��� ����Ǳ� �� ���� ���� �̸�
	String path2 = "";
	//������ ���� ���ε��ϴ� ����
	String test = "C:\\opensource_workspace\\main_web\\Auction_Site\\src\\main\\webapp\\file";
	
	try{
		MultipartRequest multi = new MultipartRequest(request, test, size,"UTF-8",new DefaultFileRenamePolicy());
		String name = multi.getParameter("name");			//multi��ü�� �Ķ���� �޾ƿ;ߵ�
		String content = multi.getParameter("content");
		String category = multi.getParameter("category");
		int price = Integer.parseInt(multi.getParameter("price"));
		String year = multi.getParameter("year");
		String month = multi.getParameter("month");
		String day = multi.getParameter("day");
		
		String due_date = year+month+day;
	    String date = "";
	        
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
		//Date�� �����ϱ� ���� ��¥ ������ yyyy-mm-dd�� ����
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date tempDate = null;
			    
		//yyyymmdd�ε� ��¥ �������� java.util.Date��ü�� ����
		tempDate = beforeFormat.parse(due_date);
		out.print(tempDate);	   
		//Date�� yyyy-mm-dd �������� �����Ͽ� String���� ��ȯ
		date = afterFormat.format(tempDate);
	    
	    out.println("date: " + date);
		
	    //String �� java.sql.Date ��ȯ 
	    String day2 = date; // ������ ���Ѿ� ��
	    java.sql.Date d = java.sql.Date.valueOf(day2);
	    PrDAO PDAO = new PrDAO();
	    
	    Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();	//���� �̸��� �޾ƿ� ��Ʈ������ ����
		file = multi.getFilesystemName(str);	//���ε� �� ���� �̸� ������
		//out.println(file);
		String user_id = (String)session.getAttribute("user_id");
		
		int result = PDAO.write2(name, category, price, content, d, file, user_id);
	    //int i = PDAO.insert(name, content, category, price, d, file);	//sql���� date��ȯ �� db����
	    
	    //������
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
			script.println("location.href = 'home2.jsp'");
			script.println("</script>");
		}
	} catch (Exception e){
		e.printStackTrace();
	}
%>
</body>
</html>