<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String id = null;
	if(session.getAttribute("user_id") != null){
		id = (String) session.getAttribute("user_id");
	}
%>
<style>
	.title{
		margin-top:50px;
	}
	.box{
		text-align:center;
	}
	.text{
		font-size:15px;
	}
	.area{
		font-size:20px;
		font-family: inherit;
	}
</style>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
 <meta charset="UTF-8">
    <title>Testing websockets</title>
</head>
<body>
<div class="title">
<center class="page-content page-container" id="page-content">
<center class="publisher bt-1 border-light"><img class="avatar avatar-xs" src="https://img.icons8.com/color/36/000000/administrator-male.png" alt="...">ID : <%=id %></center> 
</div>
    <div class="box">
      	<textarea class = "area" id="messageWindow" rows="20" cols="50" readonly="true"></textarea><br>
        <input class = "text" id="inputMessage" type="text"/>
        <input type="submit" value="send" onclick="send()" />
    </div>
</body>
 <script type="text/javascript">
     var textarea = document.getElementById("messageWindow");
     var webSocket = new WebSocket('ws://localhost:8080/Auction_Site/broadcasting');	//ws://localhost:8080/프로젝트명/broadcating
     var inputMessage = document.getElementById('inputMessage');
    webSocket.onerror = function(event) {
      onError(event)
    };
    webSocket.onopen = function(event) {
      onOpen(event)
    };
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    function onMessage(event) {
        textarea.value += "상대 : " + event.data + "\n";
    }
    function onOpen(event) {
        textarea.value += "연결 성공\n";
    }
    function onError(event) {
      alert(event.data);
    }
    function send() {
        textarea.value += "나 : " + inputMessage.value + "\n";
        webSocket.send(inputMessage.value);
        inputMessage.value = "";
    }
 </script>