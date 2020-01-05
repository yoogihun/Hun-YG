<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세조회</title>
</head>
<body>
	
	<table class="table table-board" border="1px" width="80%" align="center">
        <tr>                     
            <th style="width:30%">제목</th>
            <th style="width:20%">내용</th>
            <th style="width:20%">날짜</th>
            <th style="width:20%">작성자</th>
        </tr>
 	
        <tr>
            <td>${boardVO.b_no }  </td>
            <td>${boardVO.b_detail} </td>
            <td>${boardVO.b_date } /> </td>
            <td>${boardVO.b_writer }</td>
        </tr>
    
    <c:if test="${boardVO.b_writer == member.userName }">
   	 	<input type = button name="update" onclick = "location.href='/board/update?b_no=${boardVO.b_no}'" value="수정하기">
 		<input type = button name="delete" onclick = "location.href='/board/delete?b_no=${boardVO.b_no}'" value="삭제하기">	
    </c:if>
    	<input type = button name="delete" onclick = "location.href='/board/listAll'" value="글 목록">   
    
    
        
   
    </table>
    <tr>
		<th style="width:10%">댓글 작성자</th>
		<th style="width:10%">댓글내용</th>
					 
	</tr>
    <div>
			 <ol class="reply">
				 <c:forEach items="${reply}" var="reply">
					 
					<tr>
						댓글작성자 : ${reply.writer}
						댓글내용: ${reply.content}
						</br>
					
					</tr>
						   
					 
				 </c:forEach>   
			 </ol>
		</div>
		
<c:if test="${msg != false }">
	<form action="/board/detail" method="post">
		<input type="hidden" id="b_no" name="b_no" value="${boardVO.b_no }" >
		
		<input type="hidden" id="r_no" name="r_no" value="${RNOCK.r_no }" >
		
		<label>댓글 작성자</label>
		<input type="text" id="writer" name="writer" value="${member.userName}" readonly="readonly"> 
		
		<label>댓글내용</label>
		<input type ="text" id="content" name="content">
		
		<input type="submit" value="댓글작성">
	</form>
</c:if>
</body>
</html>