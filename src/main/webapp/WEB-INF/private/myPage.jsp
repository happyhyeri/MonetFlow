<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel="stylesheet"/>
<meta charset="UTF-8">
<meta name="viewport" content="width=divice-width, initial-scale=1.0">
<title>M Y P A G E</title>
</head>
<body>
<%@ include file="/WEB-INF/private/privateNav.jsp" %>
		<div>
			<a href="${pageContext.servletContext.contextPath }/private/myPage">
			<img src="${pageContext.servletContext.contextPath }${sessionScope.logonUser.avatar.imageUrl }" style="width: 36px;"/>
			</a>
			<b>${sessionScope.logonUser.nickname }</b>님이 로그온 중입니다.  
			
			
		</div>

	
	<h2>회원정보 변경</h2>
	<c:if test="${result }">
			<div>
			<p> ❌ 회원정보 변경을 실패했습니다 ❌ </p>
			<p>닉네임을 다시 설정해주세요</p>
			</div>
	</c:if>

	<form action="${pageContext.servletContext.contextPath }/private/myPage" method="post">
		
			<div  class="small-font"> 
			<label>닉네임(*)</label>
			</div>
			
			<div>
				<input name="nickname" value="${logonUser.nickname}" required class="medium-font" />
			</div>
		 
			<div class="small-font">
			<label>탄생 년도(*)</label>
			</div>
			
			
			<div>
				<select name="birth">
					<c:forEach var="year" begin="1923" end="2023">
						<option ${year eq logonUser.birth ? 'selected' : '' }>${year }</option>년
					</c:forEach>
				</select>
			</div>
	
		
			<div class="small-font">
			<label>성별(*)</label>
			</div>

			<div>
				 <input type="radio" name="gender" value="M" id="male" class="medium-font"  ${logonUser.gender eq "M" ? 'checked' : '' }/> <label for="male">남성</label> 
					<input type="radio" name="gender" value="F" id="female" class="medium-font" ${logonUser.gender eq "F" ? 'checked' : '' }> <label for="female">여성</label> 
					<input type="radio" name="gender" value="" id="" class="medium-font" ${logonUser.gender eq "" ? 'checked' : '' }/> <label for="">비공개</label>
			</div>
		
		
		
			<div class="small-font"><label>아바타 선택하기(*)</label></div>
			
			<div class="medium-font">
				<c:forEach var="one" items="${avatars }">
				
					<div style="display: inline-block;">
						<label for="${logonUser.id }-radio">
						<img src="${pageContext.servletContext.contextPath }${one.imageUrl }" style="width: 110px" />
						</label>
					</div>
					
					<div>
						<input type="radio" name="avatarId" value="${one.id }"
							id="${one.id }-radio"
							${logonUser.avatar.imageUrl eq one.id ? 'checked' : '' } />
					</div>
			</c:forEach>

		

		<button class="button medium -font">변경하기</button>
		
		

	</form>
</body>
</html>