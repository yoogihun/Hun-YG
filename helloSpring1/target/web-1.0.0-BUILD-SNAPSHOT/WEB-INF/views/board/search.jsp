<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 
</head>
<body>
 
    <table class="table table-board" border="1px" width="80%" align="center">
        <tr>
            <th style="width:10%" >글 번호</th>         
            <th style="width:30%">제목</th>
            <th style="width:20%">작성자</th>
            <th style="width:20%">날짜</th>
            <th style="width:20%">조회수</th>
        </tr>
 	
        <tr>
            <td>${boardVO.b_no }</td>
            <td><a href="/board/detail?b_no=${boardVO.b_no}">${boardVO.b_title }</a></td>
            <td>${boardVO.b_writer }</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.b_date }" /> </td>
            <td><span> ${boardVO.b_count }</span> </td>
        </tr>
    </table>
    
    <c:if test="${msg == null }">
    	<input type ="button" name ="write" value="글작성" onclick="location.href='/board/create'">
    	<input type ="button" onclick="location.href='/member/login'" value="로그인화면">
    	</c:if>
    
    <c:if test="${msg != null }">
    	
    	<input type ="button" onclick="location.href='/member/register'" value="가입하기">
    	<input type ="button" onclick="location.href='/member/login'" value="로그인">
    
    </c:if>
    
     
</body>
</html>

