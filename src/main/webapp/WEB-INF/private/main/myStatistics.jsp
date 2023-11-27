<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel="stylesheet"/>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>나의 소비 분석표</title>
</head>
<body>
<%@ include file="/WEB-INF/private/privateNav.jsp" %>

<h2> ${logonUser.nickname } 님의 소비 분석표 </h2>

	<!-- <form action ="${pageContext.servletContext.contextPath }/private/main/myStatistics"> -->
		<table class="table medium-font">
				
				<tr>
					<th>월</th>
					<th>금액 합계</th>					
				</tr>
				
				<c:forEach var="one" items ="${MonthlyLists }" >		
					
					<tr>
						<td>
							<c:set var="month" value="${fn:split(one.month,'-')[1]}"/> 
				<a href ="${pageContext.servletContext.contextPath }/private/main/myStatistics?month=${fn:split(one.month,'-')[1]}">
						${month }월
				</a>
						</td>
						<td><fmt:formatNumber value="${one.sum}" pattern = "#,### 원"/></td>
						
					</tr>
				</c:forEach>
			</table>
	<!-- </form>-->
	
			<table class="table medium-font">
			
				<tr>
					
					
					<th>카테고리<th>
					<th>금액 합계</th>					
				</tr>
					
				<c:forEach var="two" items ="${MonthlyCategoryList }"  >		
					<tr>
						
						<td>
						${GetCategoryName }
						</td>
						
						<td><fmt:formatNumber value="${two.sum}" pattern = "#,### 원"/></td>
						
					</tr>
				</c:forEach>
				
			</table>
	<c:url value = "/private/main/myStatistics${fn:split(one.month,'-')[1]}">
		<c:param name="month" value="${fn:split(one.month,'-')[1]}">
		</c:param>
	</c:url>
</body>
</html>