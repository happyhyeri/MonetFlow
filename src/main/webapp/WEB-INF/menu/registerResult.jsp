<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel="stylesheet"/>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>R E G I S T E R R E S U L T</title>
</head>
<body>
<div class="align-right medium-font">
<a href = "${pageContext.servletContext.contextPath }/index"> 🤍메인으로🤍</a>
</div>

<h1>회 원 가 입 결 과</h1>

	<c:choose>
		<c:when test="${result eq 1 }">
			<div>
			<p>😊 ${saveUser.nickname }님 환영합니다 😊</p>
			<p>우리 함께 돈 머니머니 모아서 부자 되자구요💸
			</div>
		</c:when>
		<c:otherwise>
			<div>
			<p>😳 ${tempUser.id }는 이미 존재하는 아이디입니다.😳 </p>
			<p>다시 시도해주시겠어요?</p>
			</div>
			
			<div class="align-center medium-font">
			<a href = "${pageContext.servletContext.contextPath }/menu/register"> 이전페이지로 돌아가기</a>
			</div>
		</c:otherwise>
	</c:choose>


</body>
</html>