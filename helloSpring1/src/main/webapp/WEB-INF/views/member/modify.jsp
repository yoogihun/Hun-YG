<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
</head>
<body>
	
	<form action ="/member/modify" method="post">	
		<label>아이디</label>
		<input type="text" name="userID" value="${member.userID }"/>
		<label>비밀번호</label>
		<input type="text" name="userPW">
		
		<input type="submit" value="회원 정보 수정">
	</form>
	
	<p> <a href="/member/login">처음으로</a> </p>
	
</body>
</html>