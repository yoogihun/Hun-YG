<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.min.js"></script>    
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    
    
    <title>게시판</title>
    <style type="text/css">
    	#detail{
    		display:inline; color:blue;
    		
    	}
    	
    	#tit{
    		display:inline; color:blue;
    	}
    	#b_title{
    		border: 1px solid red; 
    	}
    	
    	.createForm{
    		border: 100px
    	}
    	
    	#realtitle{
    		color:blue;
    	}
    
    </style>
</head>
<body>
<h1 id="realtitle">
    게시판 글쓰기 
</h1>

<c:if test="${msg != false }">
    <form name="frm1" action="/board/create" method="POST">
        <div class="createForm">
            <li>제목 </li>
            	<ol id="tit"><input type="text" id="b_title" name="b_title" class="createForm" placeholder = "게시판 제목"></ol>
        </div>
        <br/>
        <div class="createForm">
            <li >내용</li>
            <ol><input type="text" id="b_detail" name="b_detail" placeholder = "게시판 내용"></ol>
        </div>
        
            <li >작성자:
            <input type="text" name="b_writer" class="createForm" value="${member.userName }" readonly="readonly">
        </li>
        <br/>
            <input type="submit" value="글작성">
             
    </form>
</c:if>

<c:if test="${msg == false }">
	<p style="f#00;">로그인을 해야 글을 쓸수 있지롱</p>
	
	<p><a href="/member/login">로그인</a>
</c:if>

<!-- ==================== 이미지 등록 -->
<form name="frm2"  method="post" autocomplete="off" enctype="multipart/form-data">
<div class="inputArea">
 <label for="gdsImg">이미지</label>
 <input type="file" id="gdsImg" name="file" />
 <div class="select_img"><img src="" /></div>
</div>
</form>

 <script>
  $("#gdsImg").change(function(){
   if(this.files && this.files[0]) {
    var reader = new FileReader;
    reader.onload = function(data) {
     $(".select_img img").attr("src", data.target.result).width(500);        
    console.log(data.target.result);
    }
    reader.readAsDataURL(this.files[0]);
   }
  });
 </script>
 
<script type="text/javascript">
	function gihun(){
		var form = $('form')[0];
		var formData = new FormData(form);
		
		var formImg = $('form')[1];
		//var formDataImg = new FormData(formImg);
		var formDataImg = new FormData();
		
		
		$('#gdsImg').on('change',function(e){
			const reader = new FileReader();
			
		})
		formDataImg.append("file");
		
		console.log(formImg);
		$.ajax({
			cache: false,
			url: "/board/create",
			processData:false,
			contentType:false,
			type:'POST',
			data:formData,
			success : function(data) {
                var jsonObj = JSON.parse(data);
            }, // success 
    
            error : function(xhr, status) {
                alert(xhr + " : " + status);
            }
			})
			
		$.ajax({
			cache: false,
			url: "/board/ImgUpload",
			processData:false,
			contentType:false,
			type:'POST',
			data:formDataImg,
			success : function(data) {
               console.log(formDataImg);
            }, // success 
    
            error : function(xhr, status) {
            	console.log(formDataImg);
            }
			})
		}
</script>
<button type="button" value="등록" onclick="gihun()"> 왜안되냐</button>

</body>
</html>

