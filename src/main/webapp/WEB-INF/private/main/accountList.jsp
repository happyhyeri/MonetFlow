<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/functions.tld" prefix="tld" %>

<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel="stylesheet"/>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>A C C O U N T L I S T</title>
</head>
<body>
<%@ include file="/WEB-INF/private/privateNav.jsp" %>
<h2>${logonUser.nickname } 님의 가계부</h2>
<div class="white-background small-font">
<c:choose>
		<c:when test ="${deleteResult eq true}">
			<div>
				<p>  삭제 성공 ---- o(*￣▽￣*)o </p>				
			</div>
		</c:when>	
		
		<c:when test ="${deleteResult eq false}">
				<div>
					<p> ❌ 삭제 실패 ❌ </p>
				</div>		
		</c:when>
	</c:choose>
</div>

<h3>소 비 내 역</h3>
		<form action = "${pageContext.servletContext.contextPath }/private/main/accountList">
		<div style="display: flex; justify-content: space-between; gap: 2px; align-items: center">	
			<div>
				<select name="sort">
					<option value="spendAt" ${param.sort eq 'spendAt' ? 'selected' :'' }>날짜순</option>
					<option value="amt" ${param.sort eq 'amt' ? 'selected' :'' }>금액순</option>
				</select>
			</div>
			
			<div>
				<span>
						<input type = "date" name = "begin" value="${param.begin}"  >
				</span>
				
				<span>
						<input type = "date" name = "end" value="${param.end}"  >
				</span>
				
			</div>	
			<!-- 카테고리 부분 -->
			<div>
				<c:forEach var = "one" items="${categoryList }">
					<input type ="checkbox" name="categoryId" value="${one.id }">
					
				</c:forEach>
			
			</div>
			
				<button class="button3">검색</button>
			</div>
		</form>
	
	
		<a href = "${pageContext.servletContext.contextPath }/private/main/myStatistics"> 통계 보기 </a>
	<div>
		<form action = "${pageContext.servletContext.contextPath }/private/main/accountList" method="post">
			<table class="table medium-font">
				
				<tr>
					<th></th>
					<th>날 짜</th>
					<th>분 류</th>
					<th>금 액</th>
					<th>사용 내역</th>
				</tr>
					
					
				<c:forEach var="one" items ="${spendLogs }">		
					<tr>
						<td>
							
							<input type = "checkbox" name ="no" id="check1" value="${one.no }">
							
						</td>
						<td>${one.spendAt }</td>
						<td>${one.category.name}</td>
						<td>${one.amt }</td>
						<td>${one.useDesc }</td>
					</tr>	
				</c:forEach>
					<tr>
						<td colspan='5'>총 합계 : ${total } 원</td>
					</tr>
			</table>
			<div class="align-left"style="display: flex; justify-content: space-between;">
						<button class="button2"> 삭제 </button>
			</div>
			
			
		</form>
		
	</div> 

</body>
</html>