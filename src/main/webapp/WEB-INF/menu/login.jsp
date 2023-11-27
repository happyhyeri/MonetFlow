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
<title>L O G I N</title>
</head>
<body>
<%@ include file="/WEB-INF/nav.jsp" %>
<h1>
<a href = "${pageContext.servletContext.contextPath }/index">💰 M O N E Y F L O W 💰</a>
</h1>

<form action = "${pageContext.servletContext.contextPath }/menu/loginResult" class="login-form" method="post">
	<c:if test="${error }">
						<div>
			<p> ❌ 로그인 실패했어요 ❌ </p>
			<p>다시 시도해보시겠어요?</p>
			</div>
	</c:if>
	
	
	<label >
	<div>I D</div>
		<div>
			<input name="loginId" required class="input"/>
		</div>
	</label>	
	
	<label>
	<div>PASSWORD</div>
		<div>
			<input type="password" name="loginPassword" required class="input"/>
		</div>
	</label>
	
	<label>
	<input type = "checkbox" name = "keep" value="true" > 로그인 상태 유지 <br>
	</label>
	<button class="button " >로그인</button>

</form>	


<p>
<a href ="${pageContext.servletContext.contextPath }/menu/register" class = "small-font" >MONEY FLOW가 처음이신가요? </a> 
</p>


</body>
</html>