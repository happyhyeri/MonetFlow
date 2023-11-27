<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=divice-width, initial-scale=1.0">
<link
	href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css"
	rel="stylesheet" />
<title>R E G I S T E R</title>
</head>
<body>
<%@ include file="/WEB-INF/nav.jsp" %>
	
	<h1>회 원 가 입</h1>

	<h3>기본 정보 입력</h3>
	<form
		action="${pageContext.servletContext.contextPath }/menu/registerResult"
		method="post">
		
					<c:if test="${error }">
						<div >${tempUser.id } 은/는 이미 사용중인 아이디 입니다.
						</div>
					</c:if>
					
			<div class="small-font">
			<label>사용할 아이디(*)</label>
			</div>
			<div>
				<input name="id" required class="medium-font" />
			</div>
		
		
		
			<div class="small-font">
			<label>사용할 비밀번호(*)</label>
			</div>
			<div>
				<input type="password" name="password" required class="medium-font" />
			</div>
		

		<h3>추가 정보 입력</h3>
	
			<div class="small-font">
			<label>닉네임(*)</label>
			</div>
			
			<div>
				<input name="nickname" required class="medium-font" />
			</div>
			
			
			
			<div class="small-font">
			<label>탄생 년도(*)</label>
			</div>
			
			<div>
				<select name="birth">
					<c:forEach var="year" begin="1933" end="2023">
						<option ${year eq 2003 ? 'selected' : '' }>${year }</option>년
					</c:forEach>
				</select>
			</div>
	
			<div class="small-font">
			<label>성별(*)</label>
			<div>
				<input type="radio" name="gender" value="M" id="male"class="medium-font" /><label for="male">남성</label>
				<input type="radio" name="gender" value="F" id="female" class="medium-font"> <label for="female">여성</label>
				<input type="radio" name="gender" value="" id="" class="medium-font" /> <label for="">비공개</label>
			</div>

		
			<div class="small-font">
			<lable>아바타 선택하기(*)</lable>
			</div>
			
			<div class="medium-font">
				<c:forEach var="one" items="${avatars }">
					<div style="display: inline-block;">
						<label for="${one.id }-radio">
						<img src="${pageContext.servletContext.contextPath }${one.imageUrl}" style="width: 110px" />
						</label>
					</div>
					<div>
						<input type="radio" name="avatarId" value="${one.id }"
							id="${one.id }-radio" ${tempUser.avatarId eq one.id ? 'checked' : '' } />
					</div>
			 	</c:forEach>
			</div>
	



		<button class="button medium -font">가입하기</button>


	</form>
</body>
</html>