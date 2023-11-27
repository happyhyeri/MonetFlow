<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel="stylesheet"/>
<title>M O N E Y F L O W </title>
</head>
</head>
<body>
<%@ include file="/WEB-INF/private/privateNav.jsp" %>

		<div class="container">
		<h3>
			<div>
			<a href="${pageContext.servletContext.contextPath }/private/myPage">
				<img src="${pageContext.servletContext.contextPath }${sessionScope.logonUser.avatar.imageUrl }" style="width: 36px;"/>
			</a>
			</div>
			
			<div>
				<b>${sessionScope.logonUser.nickname }</b>님이 로그온 중입니다.  
			</div>
		</h3>
		</div>

			<div>
				<p>😊 ${sessionScope.logonUser.nickname }님 안녕하세요 😊</p>
				<p>오늘의 소비는 어땠나요? 기록하러 갈까요?</p>
			</div>
			
</body>
</html>