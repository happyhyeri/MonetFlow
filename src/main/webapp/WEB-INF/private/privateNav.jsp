<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>로 그 온</title>
</head>
<body>

<div class = "align-right small-font">
<span class= "px-4">
<a  href ="${pageContext.servletContext.contextPath }/index">메인으로</a>
</span>

<span class= "px-4">
<a  href ="${pageContext.servletContext.contextPath }/private/main/accountList">가계부</a>
</span>

<span class= "px-4">
<a  href ="${pageContext.servletContext.contextPath }/private/main/account">가계부 작성</a>
</span>

<span class= "px-4">
<a  href ="${pageContext.servletContext.contextPath }/menu/logout">로그아웃</a>
</span>
<span class= "px-4">
<a href = "${pageContext.servletContext.contextPath }/private/main/myStatistics"> 통계 보기 </a>
</span>

</div>

</body>
</html>