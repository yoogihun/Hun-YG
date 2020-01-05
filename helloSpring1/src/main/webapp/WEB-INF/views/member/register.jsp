<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="/member/register" method ="post">
	
		<label>사용할 아이디</label>
		<input type="text" id="userID" name="userID">
		
		<label>패스워드</label>
		<input type="text" id="userPW" name="userPW">
		
		<label>닉네임</label>
		<input type="text" id="userName" name="userName">
	
		<input type="submit" value="가입하기">
	</form>

</body>
</html>