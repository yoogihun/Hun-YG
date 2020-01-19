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
		
		var list_lat = new Array();
	    var list_lng = new Array();
	    
	    <c:forEach items="${poi }" var="poi">
	            list_lat.push("${poi.point_lat }");
	            list_lng.push("${poi.point_lng }");
	    </c:forEach>
	     
		//마커 표시할 위치 지정 및 마커 생성
		
		for(var i=0; i<list_lat.length;i++){
			var marker = new kakao.maps.Marker({
			    map: map,
				position: new kakao.maps.LatLng(list_lat[i],list_lng[i])
			});
			marker.setClickable(true);
			console.log(i)
			marker.setMap(map);
			marker.setDraggable(true);
			
			var	iwRemoveable = true;
			var infowindow = new kakao.maps.InfoWindow({
			    position : new kakao.maps.LatLng(list_lat[i],list_lng[i]), 
			    content : '위도:'+list_lat[i]+"     "+'<br/>경도:'+list_lng[i],
			    removable : iwRemoveable
			});
			      // 마커 위에 인포윈도우를 표시합니다
			infowindow.open(map,marker);
			      kakao.maps.event.addListener(marker, 'click', function() {
		          // 마커 위에 인포윈도우를 표시합니다
		          infowindow.open(map,marker);  
		    });
		}
		
		
		// 마커가 지도 위에 표시되도록 설정합니다
		
		
		
		kakao.maps.event.addListener(map, 'click', function(mouseEvent){
	   		
			// 클릭한 위도, 경도 정보를 가져옵니다 
		   	    var latlng = mouseEvent.latLng;
			
		   	 var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
		   	    message += '경도는 ' + latlng.getLng() + ' 입니다';
		   	 
		   	    var resultDiv = document.getElementById('result');
		   	    console.log(resultDiv);
		   	    resultDiv.innerText = message;
		});	
		 
		
		</script>
		

	
<!-- 지점 등록 부분 -->	
   <script>
    var setPopup;
	var lat;
	var lng;
	
	function popup(){
		
        var url = "/board/point_reg_Popup";
        var name = "point_regist_popup";
        var option = "width = 700px, height = 500px, top = 100, left = 200, location = no"
        setPopup = window.open(url, name, option);
	}
	
	
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
			// 마커 드래그 가능
			marker.setDraggable(true);
			
			lat = latlng.getLat(),
			lng = latlng.getLng();
			
			
			
		        
		    	
			
			var iwContent = '지점명:'+'<input type = "text" id = "point_name" name="point_name" placeholder="입력"><br/>위도:'+lat+'<br/>경도:'+lng,
		    	iwRemoveable = true;
				iwPosition = new kakao.maps.LatLng(lat, lng); //인포윈도우 표시 위치입니다
		    console.log(iwContent);
			// 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
		    position : iwPosition, 
		    content : iwContent,
		    removable : iwRemoveable
			});
			console.log(lat+"hhh");
			console.log(lng+"gg")
				popup();
				
			
			
			 			 		
   	    console.log(latlng+"test")
   	   
   	    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
   	    message += '경도는 ' + latlng.getLng() + ' 입니다';
   	 
   	    var resultDiv = document.getElementById('result');
   	    console.log(resultDiv);
   	    resultDiv.innerText = message;
   	 	
   		
   	    
   	    
   	    // 마커에 클릭이벤트를 등록합니다
   	 	kakao.maps.event.addListener(marker, 'click', function() {
   	    // 마커 위에 인포윈도우를 표시합니다
   	  	infowindow.open(map, marker);
   	 	});
		});
		
	}
    
    function set(){
		setPopup.document.getElementById("point_lat").value = lat;
	    setPopup.document.getElementById("point_lng").value = lng;	
			
	}
    </script>
   
   <script>
   	function mvcenter(){
   		// 이동할 위도 경도 위치를 생성합니다 
    	var moveLatLon = new kakao.maps.LatLng(33.452613, 126.570888);
    	// 지도 중심을 이동 시킵니다
    	map.setCenter(moveLatLon);
   	}
   
   </script>
   
	
   <input type="button" id = "btn" name ="btn" value = "지도 중심 이동하기">
   <script>
    	var center = document.getElementById("btn");
    	center.addEventListener('click', mvcenter);
    			
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
    
    
    
    
    <input type ="button" id="reg" name="reg" value="마커생성">
     <script>
    	var regist = document.getElementById("reg");
    	regist.addEventListener('click', marker);
    </script>
    
   
   
   
   
   
	<input type ="button" onclick="location.href='/board/listAll'" value="게시판이동">
	
    
    
    <input type="button" onclick="popup()" value="팝업">
    <input type="button" onclick="set()" value="전달">
	
	
	

    
</body>
</html>

