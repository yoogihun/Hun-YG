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
opener.location.href = "javascript:modset();";

function modsub(){
	var point_modify = document.point_mod;
	point_modify.submit();
	close();
}

</script>
	
		<form name="point_mod" method="POST">
		
			<input type="text" id="point_id" name="point_id" readonly="readonly">
			<label>지점명 :</label>
			<input type="text" id="point_name" name="point_name">
				<br/>
			<label>위도:</label>
			<input type="text" id="point_lat" name="point_lat" >
				<br/>
			<label>경도:</label>
			<input type="text" id="point_lng" name="point_lng" >
				<br/>
			<input type="button" onclick="modsub()"  value="만들기">
		</form>
	
	
	
	
		
	
</body>
</html>