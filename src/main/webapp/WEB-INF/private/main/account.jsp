<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel="stylesheet"/>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>A C C O U N T</title>
</head>
<body>
<%@ include file="/WEB-INF/private/privateNav.jsp" %>
	<h1>${logonUser.nickname } 님의 가계부</h1>
	
	<div class="white-background small-font">
	<c:choose>
		<c:when test ="${saveResult}">
			<div>
				<p> 😊 기록 완료!😊 </p>
				
			</div>
		</c:when>	
		
		<c:when test ="${saveResult eq false}">
				<div>
					<p> ❌ 기록이 저장되지 않았습니다. ❌ </p>
					<p>다시 시도해보시겠어요?</p>
				</div>		
		</c:when>
	</c:choose>
	</div>

	<h3>지 출 내 역</h3>
	<form action="${pageContext.servletContext.contextPath }/private/main/account" method="post">
	<table class = "table" >
		<tr class = "table" >
			
			<th>분 류 </th>
			<th>지출 금액</th>
			<th>사용 날짜</th>
			<th>사용 내역</th>
		</tr>
				
		<tr class = "table" >
			
			<div>
				<td>
				<div class="form-container">
					<select name="category" >
						<c:forEach var="one" items ="${ctpList }">
							<option value="${one.id }" >${one.name }</option>
						</c:forEach>
					</select>
				</div>
				</td>	
			</div>
			
		
			<div>
				<td>
					<input type = "number" name = "money">
				</td>
			</div>
				
				<td>	
			<div style= " color:color:#F1F0E8; ">
					<input type = "date" name = "date" value="${now}" max="${now}" >
			</div>
				</td>
				
			<td>
				<div>
					<input name = "content">
				</div>
			
			</td>
		</tr>
			
		</table>	
		
			<button class="button">완 료</button>
			
		</form>
		

</body>
</html>