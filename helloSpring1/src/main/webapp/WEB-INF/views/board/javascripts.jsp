<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>카카오지도</title>
    <p>지도는 아래다</p>
    
    

    
    
</head>
<body>
	<div id="map" style="width:500px;height:300px;"></div>
    <p><em>지도를 클릭해주세요!</em></p>
    <p id="result">test</p>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c3658373b4fe9b55a40e5d8fa1d840f2"></script>
    
    
    <script>
	    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
	    };
    	
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    
		kakao.maps.event.addListener(map, 'click', function(mouseEvent){
   		 // 클릭한 위도, 경도 정보를 가져옵니다 
   	    var latlng = mouseEvent.latLng;
   		
   	    console.log(latlng+"test")
   	   
   	    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
   	    message += '경도는 ' + latlng.getLng() + ' 입니다';
   	    
   	    var resultDiv = document.getElementById('result');
   	    console.log(resultDiv);
   	    resultDiv.innerText = message;
   	});
		</script>
   
    
   
   <script>
    	function setCenter(moveLatLon) {            
        // 이동할 위도 경도 위치를 생성합니다 
        	var moveLatLon = new kakao.maps.LatLng(33.452613, 126.570888);
        // 지도 중심을 이동 시킵니다
        	map.setCenter(moveLatLon);
        
        
    	}
    	
   
    	
    
    </script>
   <input type="button" id = "map" name ="map" value = "지도 중심 이동하기" onclick = "setCenter()">
    
	
    
</body>
</html>

