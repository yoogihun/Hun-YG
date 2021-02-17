<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	
   	 
     <form action ="/board/replyupdate" method="POST" accept-charset="UTF-8">
     		
            <input type="text" id = "b_no" name="b_no" value="${rep.b_no }" readonly="readonly">
            <input type="text" id = "r_no" name="r_no" value="${rep.r_no }" readonly="readonly">
        
     		
            <label >작성자</label>
            <input type="text" id="writer" name="writer" value="${rep.writer }">
        
        
            <label >댓글내용</label>
            <input type="text" id="content" name="content" value="${rep.content }">
            
            <input type="submit" value="수정완료">                   
    </form>