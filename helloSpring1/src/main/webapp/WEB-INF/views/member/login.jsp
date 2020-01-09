<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

<c:if test="${member == null }">
	<form action="/member/login" method="post">
		<label>아이디</label>
		<input type="text" id="userID" name="userID">
		
		<label>패스워드</label>
		<input type="text" id="userPW" name="userPW">
		
		<input type="submit" value="로그인">
		</br>
		<a href="/member/register">회원가입</a>
	</form>
</c:if>

<c:if test="${msg == false }">
	<p style="color:#f00;">로그인에 실패 했습니다.
</c:if>

<c:if test="${member != null }">
	<p>${member.userName }님 환영합니다.</p>
	<a href="/board/listAll">글 목록 보기</a> 
	<a href="/member/logout">로그아웃</a>
	<input type="button" onclick="location.href='/member/modify'" value="비밀번호 수정">
</c:if>
	
	
</body>
</html>