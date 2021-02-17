<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<!-- 제이쿼리 -->
 <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<meta charset="UTF-8">
<title>글 수정하기</title>
</head>
<body>
	
   	 
     <form action ="/board/update" method="POST">  		
            <input type="hidden" name="b_no" value="${boardVO.b_no }" readonly="readonly">
		
            <label >제목</label>
            <input type="text" name="b_title" value="${boardVO.b_title }">
            
            <label >내용</label>
            <input type="text" name="b_detail" value="${boardVO.b_detail }"> 
            <button type="submit" class="btn_button">수정완료</button>                    
    </form>
    <h2>이미지 수정</h2>
 	<div class="inputArea">
 		<form method="post" action="/board/modifyImg" enctype="multipart/form-data">
		 <label for="gdsImg">이미지</label>
		 <input type="file" id="gdsImg" name="file" />
		 <div class="select_img">
		  <img src="${ImgVo.gdsImg}" />
		  <input type="hidden" name="gdsImg" value="${ImgVo.gdsImg}" />
		  <input type="hidden" name="gdsThumbImg" value="${ImgVo.gdsThumbImg}" /> 
		  
		  <input type="submit" value="이미지수정">
 		</form>
 	</div>	
    
	
	
</body>
</html>