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
<script type="text/javascript">
    var result ='${msg}';
    
    if(result == '성공'){
        alert(result+'입니다.');
    }else{
       
    }
    
    /* function select(obj){
    	perPageNum.set("perPageNum", obj.value)
    	document.perPageNum.submit();
    } */ 
    
    
</script>

 <style>
 	#he{
 		color:red;
 	}
 
 </style>


</head>
<body>
 	<script>
 	
 	</script>
 	
    <table class="table table-board" border="1px" width="80%" align="center">
        <tr>
        	<th style="width:10%" ></th>
            <th style="width:10%" >글 번호</th>         
            <th style="width:30%">제목</th>
            <th style="width:20%">작성자</th>
            <th style="width:20%">날짜</th>
            <th style="width:20%">조회수</th>
        </tr>
 	
 
    <c:forEach items="${boardList }" var="boardVO">
        <tr>
        	<td><input type="checkbox" id="ck" name="ck" value="true" onclick ="this.form.submit()"></td>
            <td>${boardVO.b_no }</td>
            <td><a href="/board/detail?b_no=${boardVO.b_no}">${boardVO.b_title }</a></td>
            <td>${boardVO.b_writer }</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.b_date }" /> </td>
            <td><span> ${boardVO.b_count }</span> </td>
        </tr>
    </c:forEach>
    </table>
    
    <div>
    <c:if test="${msg == null }">
    	<input type ="button" name ="write" value="글작성" onclick="location.href='/board/create'">
    	<input type ="button" onclick="location.href='/member/login'" value="로그인화면">
    	</c:if>
    </div>
    <c:if test="${msg != null }">
    	
    	<input type ="button" onclick="location.href='/member/register'" value="가입하기">
    	<input type ="button" onclick="location.href='/member/login'" value="로그인">
    
    </c:if>
    
    <form action ="/board/search" method="post">
    	<input type = "text" name="b_no" id="b_no" >
    	
    	<input type = "submit" value="조회">
    	
    </form>
    
    <form action ="/board/listAll" method="post">
    
    	<select id="perPageNum" name ="perPageNum" onchange="this.form.submit()" >
			<option value="" selected disabled>표현개수를 선택</option>              
			<option value="5">5개</option>
			<option value="10">10개</option>
			<option value="15">15개</option>
		</select>
    		
    </form>
    
    <form action ="/board/listAll" method="post">
    
    	<input type="radio" name="perPageNum" id="perPageNum" value="5" onclick="this.form.submit()">5개씩
    	<input type="radio" name="perPageNum" id="perPageNum" value="10" onclick="this.form.submit()">10개씩
    	<input type="radio" name="perPageNum" id="perPageNum" value="15" onclick="this.form.submit()">15개씩
    		
    </form>
    
    
    	
    
	    
<ul class="btn-group pagination">
    
    
    	<li>
        	<a href='<c:url value="/board/listAll?page=1"/>'><i class="fa fa-chevron-left">처음</i></a>
    	</li>
    
    
    <c:if test="${pageMaker.prev }">
   
    <li>
        <a href='<c:url value="/board/listAll?page=${pageMaker.startPage-1 }"/>'><i class="fa fa-chevron-left">이전</i></a>
    </li>
    </c:if>
    
    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
    <li>
    	
        <a href='<c:url value="/board/listAll?page=${pageNum }"/>'><i class="fa">${pageNum }</i></a>
    </li>
    </c:forEach>
    
    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
    
    <li>
        <a href='<c:url value="/board/listAll?page=${pageMaker.endPage +1}"/>'><i class="fa fa-chevron-right">다음</i></a>
    </li>
    </c:if>
    
    <li>
        <a href='<c:url value="/board/listAll?page=${pageMaker.theend }"/>'><i class="fa fa-chevron-right">맨끝</i></a>
    </li>
</ul>
	
<input type ="button" onclick="location.href='/board/javascripts'" value="지도">
<a href="/board/javascripts">지도</a>


<input type="button" id="download" value="다운로드">

<script>
document.getElementById('download').addEventListener('click',function(){
	
	var filePath = "C:/tmp/test.txt";
    var fileName = "test.txt";
                
    location.href = "/contract/fileDownload?filePath="+filePath+"&fileName="+fileName;
    
})




</script>
</body>
</html>

