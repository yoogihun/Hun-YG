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
	<div id="map" style="width:800px;height:500px;"></div>
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
    	
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();
		
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOLEFT);
		
	
		
		
		
		
		
		/*kakao.maps.event.addListener(map, 'click', function(mouseEvent){
   		 // 클릭한 위도, 경도 정보를 가져옵니다 
   	    var latlng = mouseEvent.latLng;
   		//마커 표시할 위치 지정
		var markerPosition  = new kakao.maps.LatLng(latlng.getLat(),latlng.getLng());
		console.log(markerPosition+"gihun");
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});
		
		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		marker.setDraggable(true);
		
		
		var iwContent = '<div style="padding:5px;">Hello World! <br><a href="https://map.kakao.com/link/map/Hello World!,latlng.getLat(), latlng.getLng()" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,latlng.getLat(), latlng.getLng()" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	    	iwPosition = new kakao.maps.LatLng(latlng.getLat(), latlng.getLng()); //인포윈도우 표시 위치입니다
	    
		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
		});
		infowindow.open(map, marker);
   	    console.log(latlng+"test")
   	   
   	    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
   	    message += '경도는 ' + latlng.getLng() + ' 입니다';
   	    
   	    var resultDiv = document.getElementById('result');
   	    console.log(resultDiv);
   	    resultDiv.innerText = message;
   		});*/
		
		
		</script>
   
   
	
   
   <script>
    	function setCenter(moveLatLon) {            
        // 이동할 위도 경도 위치를 생성합니다 
        	var moveLatLon = new kakao.maps.LatLng(33.452613, 126.570888);
        // 지도 중심을 이동 시킵니다
        	map.setCenter(moveLatLon);
        	
        
    	}
    </script>
    
    
    <script>
	    function getInfo() {
	    // 지도의 현재 중심좌표를 얻어옵니다 
	    var center = map.getCenter(); 
	    
	    // 지도의 현재 레벨을 얻어옵니다
	    var level = map.getLevel();
	    
	    // 지도타입을 얻어옵니다
	    var mapTypeId = map.getMapTypeId(); 
	    
	    // 지도의 현재 영역을 얻어옵니다 
	    var bounds = map.getBounds();
	    
	    // 영역의 남서쪽 좌표를 얻어옵니다 
	    var swLatLng = bounds.getSouthWest(); 
	    
	    // 영역의 북동쪽 좌표를 얻어옵니다 
	    var neLatLng = bounds.getNorthEast(); 
	    
	    // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
	    var boundsStr = bounds.toString();
	    
	    
	    var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
	    message += '경도 ' + center.getLng() + ' 이고 <br>';
	    message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
	    message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
	    message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
	    message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
	    console.log(message);
   
		}
    </script>
    
    <script>
    function marker(){
		alert("등록할 지점을 선택하세요")	
		kakao.maps.event.addListener(map, 'click', function(mouseEvent){
	   		
		// 클릭한 위도, 경도 정보를 가져옵니다 
	   	    var latlng = mouseEvent.latLng;
	   		
	   		 //마커 표시할 위치 지정
			var markerPosition  = new kakao.maps.LatLng(latlng.getLat(),latlng.getLng());
			console.log(markerPosition+"gihun");
			
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});
			
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
			marker.setDraggable(true);
			
			var iwContent = '<div style="padding:5px;">Hello World! <br><a href="https://map.kakao.com/link/map/Hello World!,latlng.getLat(), latlng.getLng()" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,latlng.getLat(), latlng.getLng()" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		    	iwPosition = new kakao.maps.LatLng(latlng.getLat(), latlng.getLng()); //인포윈도우 표시 위치입니다
		    
			// 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
		    position : iwPosition, 
		    content : iwContent 
			});
		    	
			infowindow.open(map, marker); 			 		
   	    console.log(latlng+"test")
   	   
   	    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
   	    message += '경도는 ' + latlng.getLng() + ' 입니다';
   	    
   	    var resultDiv = document.getElementById('result');
   	    console.log(resultDiv);
   	    resultDiv.innerText = message;
   	 	kakao.maps.event.stopPropagation();
		});		
	}
    
    </script>
    
    <!-- <script>
    	function al(){
    		console.log("설마?")
    		alert("Hello world");
    	}
    </script>
    
    <input type="button" id="mark" name="mark" value="버튼">
    <script type="text/javascript">
    	var ma = document.getElementById("mark");
		ma.addEventListener('click', al);
	</script> -->
   
   <input type="button" id = "map" name ="map" value = "지도 중심 이동하기" onclick = "setCenter()">
   
   <input type ="button" onclick="location.href='/board/listAll'" value="게시판이동">
	
	
	



    
</body>
</html>

