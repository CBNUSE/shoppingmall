<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="product.*"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.net.URLEncoder" %>
<%@page import="java.util.*" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.text.SimpleDateFormat" %>

<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html>
<html>
<body>
<%
	PrDAO PDAO = new PrDAO();	
	
	
		ArrayList<Bean> Alist = PDAO.getList2();
	
			for(int i=0; i<Alist.size(); i++){
				if(Alist.get(i).getBbsavailable() != 0){
					String duedate = Alist.get(i).getBbsdate();
					
					SimpleDateFormat dfhm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					
					int yyyy = cal.get(Calendar.YEAR);
					int MM = cal.get(Calendar.MONTH);
					int dd = cal.get(Calendar.DATE);
					int hh = cal.get(Calendar.HOUR);
					int mm = cal.get(Calendar.MINUTE);
					int ss = cal.get(Calendar.SECOND);
					
					cal.set(yyyy,MM,dd,hh,mm,ss);
					
					Date currentTime = new Date();
					
					
					String today = dfhm.format(currentTime);
					
					Date endDate = null;
					Date now = null;
					
					long diff = 0;
					
					endDate = dfhm.parse(duedate);
					now = dfhm.parse(today);
					
					diff = endDate.getTime() - now.getTime();
					
					if(diff <= 0){
						PDAO.delete(Alist.get(i).getId());
					}
				}	
	}
%>
</body>
</html>