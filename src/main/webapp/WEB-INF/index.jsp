<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel="stylesheet"/>
	
<title>M O N E Y F L O W</title>
</head>
<body>
<%@ include file="/WEB-INF/nav.jsp" %>
<h1>
<a href = "${pageContext.servletContext.contextPath }/index">💰 M O N E Y F L O W 💰</a>
</h1>

<h2>우리가 당신의 MONEY KEEPER가 되어드릴게요</h2>

<div class="small-font">
<p>보다 더 나은 소비습관을 갖기위해 노력하는 당신❕<br>
😊 우리와 함께 나아가 봐요 😊
</p>

<p>
당신의 소비 패턴을 분석하여 부족한 부분과 신경써야 하는 부분을 저희가 알려드릴게요!
</p>
</div>

<div class = "medium-font">
<p>현재 시간</p>
<p> <fmt:formatDate value="${time }" pattern="yyyy 년 MM 월 dd 일 HH 시 mm 분 ss 초"/> </p>
</div>






</body>
</html>