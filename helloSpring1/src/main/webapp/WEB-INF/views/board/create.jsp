<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>게시판</title>
</head>
<body>
<h1>
    게시판 글쓰기~~ 
</h1>

<div id="map" style="width:500px;height:400px;"></div>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c3658373b4fe9b55a40e5d8fa1d840f2">
    
	    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	    };
    	
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    </script>
<c:if test="${msg != false }">
    <form action="/board/create" method="POST">
        <div class="createForm">
            <label >제목</label>
            <input type="text" name="b_title" class="createForm" placeholder = "게시판 제목">
        </div>
        <div class="createForm">
            <label >내용</label>
            <textarea rows="4" cols="15" name="b_detail" class="createForm" placeholder = "게시판 내용"></textarea>
        </div>
        <div class="createForm">
            <label >작성자</label>
            <input type="text" name="b_writer" class="createForm" value="${member.userName }" readonly="readonly">
        </div>
        
        <div class="Formfooter">
            <button type="submit" class="btn_button">작성하기</button>
        </div>              
    </form>
</c:if>

<c:if test="${msg == false }">
	<p style="f#00;">로그인을 해야 글을 쓸수 있지롱</p>
	
	<p><a href="/member/login">로그인</a>
</c:if>
 
    
</body>
</html>

