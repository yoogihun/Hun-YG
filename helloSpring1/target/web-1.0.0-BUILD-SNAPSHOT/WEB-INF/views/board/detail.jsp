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
            <th style="width:10%">조회수</th>
        </tr>
 	
        <tr>
            <td>${boardVO.b_no }  </td>
            <td>${boardVO.b_detail} </td>
            <td>${boardVO.b_date } /> </td>
            <td>${boardVO.b_writer }</td>
            <td>${boardVO.b_count }</td>
        </tr>
    
    <c:if test="${boardVO.b_writer == member.userName }">
   	 	<input type = button name="update" onclick = "location.href='/board/update?b_no=${boardVO.b_no}'" value="수정하기">
 		<input type = button name="delete" onclick = "location.href='/board/delete?b_no=${boardVO.b_no}'" value="삭제하기">	
    </c:if>
    	<input type = button name="delete" onclick = "location.href='/board/listAll'" value="글 목록">
   
    </table>
    <table class="table table-board" border="1px" width="80%" align="center">
    <tr>
		<th style="width:10%">댓글 작성자</th>
		<th style="width:30%">댓글내용</th>
		<th style="width:1%"></th>
		<th style="width:1%"></th>
					 
	</tr>
    <div>
			<ol class="reply">
				 <c:forEach items="${reply}" var="reply">
					 
					<tr>
						<td align="center">${reply.writer}</td>
						<td>${reply.content}</td>
						<c:if test="${msg != false }">
						<td style="width:1%" align="right"><input type="button" id="upBtn" name="upBtn" value="수정" onclick = "location.href='/board/replyupdate?b_no=${boardVO.b_no}+&r_no=${reply.r_no}+&writer=${reply.writer }+&content=${reply.content }'">
						<td style="width:1%" align="right"><input type="button" id="delBtn" name="delBtn" value="삭제" onclick = "location.href='/board/replydelte?b_no=${boardVO.b_no}'">
						</c:if>
					</tr>
						   
					 
				 </c:forEach>   
			</ol>
	</div>
	</table>
	<div class="inputArea">
		 <label for="gdsImg">이미지</label>
		 <p>원본 이미지</p>
		 <img src="${goods.gdsImg}" class="oriImg"/>
		 
		 <p>썸네일</p>
		 <img src="${goods.gdsThumbImg}" class="thumbImg"/>
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
<p>이미지는 여기인데요</p>
<img src="${ImgVo.gdsImg }"/>

</body>
</html>