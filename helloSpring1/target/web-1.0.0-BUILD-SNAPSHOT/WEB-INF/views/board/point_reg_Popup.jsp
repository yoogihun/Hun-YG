<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
opener.location.href = "javascript:set();";

function sub(){
	var form_1 = document.point_reg;
	form_1.submit();
	close();
}
</script>


		<form name="point_reg" method="POST">
			<label>지점아이디</label>
			<input type="text" id="point_id" name="point_id">			
				<br/>
			<label>지점명 :</label>
			<input type="text" id="point_name" name="point_name">
				<br/>
			<label>위도:</label>
			<input type="text" id="point_lat" name="point_lat" >
				<br/>
			<label>경도:</label>
			<input type="text" id="point_lng" name="point_lng" >
				<br/>
			<input type="button" value="만들기" onclick="sub()">
		</form>
	
		
	
		
	
</body>
</html>