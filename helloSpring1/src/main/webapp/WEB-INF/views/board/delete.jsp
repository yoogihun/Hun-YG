<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/delete" method="post">
		<label for="b_no">정말로 삭제할껍니까?</label>
		<input type="text" id="b_no" name="b_no" value="${boardVO.b_no }">
		<br/>
		
		<button type="submit">진짜로 삭제하기</button>
	</form>
</body>
</html>