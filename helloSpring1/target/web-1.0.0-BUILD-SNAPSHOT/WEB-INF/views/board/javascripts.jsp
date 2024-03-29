<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	<title>카카오지도</title>
    <p>지도는 아래다</p>

</head>
<body>
	
	
	
	<div id="map" style="width:800px;height:500px;"></div>
    <p><em>지도를 클릭해주세요!</em></p>
    <p id="result">test</p>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c3658373b4fe9b55a40e5d8fa1d840f2"></script>
    
    
    <script>
    	
	    var overlayOn = false, // 지도 위에 로드뷰 오버레이가 추가된 상태를 가지고 있을 변수
	    container = document.getElementById('container'), // 지도와 로드뷰를 감싸고 있는 div 입니다
	    mapWrapper = document.getElementById('mapWrapper'), // 지도를 감싸고 있는 div 입니다
	    mapContainer = document.getElementById('map'), // 지도를 표시할 div 입니다 
	    rvContainer = document.getElementById('roadview'); //로드뷰를 표시할 div 입니다
	
		var mapCenter = new kakao.maps.LatLng(33.45042 , 126.57091), // 지도의 중심좌표
		    mapOption = {
		        center: mapCenter, // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
	
		// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption);	
    
    
		
		
		
	    /* var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
	    }; */
    	
		//var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    	
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();
		
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOLEFT);		
		map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
		
		
		/*  //도형 표시
		var path = [
			new kakao.maps.LatLng(33.45086654081833, 126.56906858718982),
			new kakao.maps.LatLng(33.45010890948828, 126.56898629127468),
			new kakao.maps.LatLng(33.44979857909499, 126.57049357211622),
			new kakao.maps.LatLng(33.450137483918496, 126.57202991943016),
			new kakao.maps.LatLng(33.450706188506054, 126.57223147947938),
			new kakao.maps.LatLng(33.45164068091554, 126.5713126693152)
		];

		var hole = [
			new kakao.maps.LatLng(33.4506262491095, 126.56997323165163),
			new kakao.maps.LatLng(33.45029422800042, 126.57042659659218),
			new kakao.maps.LatLng(33.45032339792896, 126.5710395101452),
			new kakao.maps.LatLng(33.450622037218295, 126.57136070280123),
			new kakao.maps.LatLng(33.450964416902046, 126.57129448564594),
			new kakao.maps.LatLng(33.4510527150534, 126.57075627706975)
		];

		// 다각형을 생성하고 지도에 표시합니다
		var polygon = new kakao.maps.Polygon({
			map: map,
		    path: [path, hole], // 좌표 배열의 배열로 하나의 다각형을 표시할 수 있습니다
		    strokeWeight: 2,
		    strokeColor: '#b26bb2',
		    strokeOpacity: 0.8,
		    fillColor: '#f9f',
		    fillOpacity: 0.7 
		}); */
		 
		
		
		
		
		var list_id = new Array();
		var list_name = new Array();
		var list_lat = new Array();
	    var list_lng = new Array();
	    var list_maxid = new Array();
	    //전역변수로 마커 사용
	    var marker;
	    var modPopup;
		var lat;
		var lng;
		
		
		function modify_popup(){
			
	        var url = "/board/point_mod_Popup";
	        var name = "point_modify_popup";
	        var option = "width = 700px, height = 500px, top = 100, left = 200, location = no"
	        modPopup = window.open(url, name, option);
		}
	    
	    
	    <c:forEach items="${poi }" var="poi">
	    		list_id.push("${poi.point_id}");
	    		list_name.push("${poi.point_name}");
	            list_lat.push("${poi.point_lat }");
	            list_lng.push("${poi.point_lng }");
	    </c:forEach>
	    
	    list_maxid.push("${maxpoi.point_id}");
	    
	    
	    
		console.log(list_maxid[0]);
	     
		//마커 표시할 위치 지정 및 마커 생성
		
		for(var i=0; i<list_lat.length;i++){
			
			//마커를 생성한다
			marker = new kakao.maps.Marker({
				map: map,
				position: new kakao.maps.LatLng(list_lat[i],list_lng[i])
			});
			//marker.setClickable(true);
			//saveId.push(marker.pd.id);
			//console.log(saveId[i]);
			//"daum.maps.Marker.Area:1"
			
			
			//마커 움직이기
			var dragable = false;
			marker.setDraggable(dragable);
			
			
			
			
			//인포윈도우를 생성한다
			var infowindow = new kakao.maps.InfoWindow({
			    position : new kakao.maps.LatLng(list_lat[i],list_lng[i]), 
			    content : "=============="+'<br/>지점아이디:'+list_id[i]+""+'<br/>지점명:'+list_name[i]+'<br/>위도:'+list_lat[i]+'<br/>경도:'+list_lng[i]+'<br/><input type="button" id="bbtn" name="bbtn" value="위치수정" onclick="modify()">',
			    removable : true
			});
			console.log(infowindow.GMid)
			//실행되면서 각 마커에 이벤트를 등록해준다.
			kakao.maps.event.addListener(marker, 'click', infow(map, marker,infowindow));
		    
			//kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	//반복문 종료		  
	}
		var thisMarker;
		var thisInfo;
		var thisId;
		//인포윈도우를 여는 함수이다. 인자 값으로 infowindow를 받는다.
		function infow(map, marker,infowindow) {
			
		    //리턴 펑션으로 하지 않으면 모든 인포윈도우가 열려버림 왜냐하면, 클릭 시, 리턴이 인포윈도우 오픈인데, 리턴이 사라지니까 이벤트 등록될때마다 실행되어버림
			return function() {
		        infowindow.open(map, marker);
		        thisMarker = marker;
		        thisInfo = infowindow;
		        thisId = marker.pd.id;
		        
		        //인포윈도우 열때 지점아이디도 세팅하는방법????
		    };
		}
		
		
		//마커 위치 수정
		var modifiyPopup;
		var mod_lat;
		var mod_lng;
		function modify(){
			alert("이동할 위치를 누르세요")
			
			//마커를 사용자가 선택한 마커로 설정해준다. saveId 값은 인포윈도우를 열어줄때, 그 해당마커를 saveId에 세팅해주고 여기서 사용한다.
			marker = thisMarker;
			Infowindow = thisInfo;
			console.log(marker);
			console.log(Infowindow);
			// 지도에 마커를 표시합니다
			marker.setMap(map);
			kakao.maps.event.addListener(map, 'click', function(mouseEvent) {	    
			    
			    var latlng = mouseEvent.latLng;
			 // 마커 위치를 클릭한 위치로 옮깁니다
			    marker.setPosition(latlng);
			 	mod_lat = latlng.getLat();	
			 	mod_lng = latlng.getLng();
			 	mod_id = thisId;
			 	console.log(lat)
			 	console.log(lng)
			 	modify_popup();
			 	
		})
		
		
		
	//수정 함수 종료
	}
		
		
	
			
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
    var markerId;
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
    	setPopup.document.getElementById("point_id").value = list_maxid[0];
		setPopup.document.getElementById("point_lat").value = lat;
	    setPopup.document.getElementById("point_lng").value = lng;
	    
			
	}
    
    function modset(){
    	modPopup.document.getElementById("point_lat").value = mod_lat;
    	modPopup.document.getElementById("point_lng").value = mod_lng;
    	modPopup.document.getElementById("point_id").value = thisId;
	}
    </script>
   
   <script>
   	function mvcenter(){
   		// 이동할 위도 경도 위치를 생성합니다 
    	var moveLatLon = new kakao.maps.LatLng(37.65231904641515, 127.02154217179361);
    	// 지도 중심을 이동 시킵니다
    	map.setCenter(moveLatLon);
   	}
   
   </script>
   
	
   <input type="button" id = "btn" name ="btn" value = "지도 중심 이동하기">
   <script>
    	var center = document.getElementById("btn");
    	center.addEventListener('click', mvcenter);
    			
    </script>
    
    
    
    <input type ="button" id="reg" name="reg" value="마커생성">
     <script>
    	var regist = document.getElementById("reg");
    	regist.addEventListener('click', marker);
    </script>
    
   
   
   
   
   
	<input type ="button" onclick="location.href='/board/listAll'" value="게시판이동">
	
    
    
    <!-- <input type="button" onclick="popup()" value="팝업">
    <input type="button" onclick="set()" value="전달"> -->
	
	<div>
		<table class="table table-board" border="1px" width="80%" align="center">
	        <tr>
	        	<th style="width:10%">지점아이디</th>
	            <th style="width:20%" >지점명</th>         
	            <th style="width:10%">위도</th>
	            <th style="width:10%">경도</th>
	        </tr>
	        
	     <c:forEach items="${poi }" var="po">   
	        <tr>
	        	<td>${po.point_id }</td>
	        	<td>${po.point_name}</td>
	        	<td>${po.point_lat}</td>
	        	<td>${po.point_lng}</td>
	        </tr>
		</c:forEach>
	</div>
	
	<input type="button" onclick="location.href='/board/roadview'" value="로드뷰">
	</body>
</html>

