<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	<!-- =================================================================================================== -->
	<!-- 기훈추가 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.min.js"></script>
	
	 
	<!-- =================================================================================================== -->
	<!-- Ajax -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
 	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
 	
 	<!-- =================================================================================================== -->
	<!-- =================================================================================================== -->

	
	
	<script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>

	
	
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"><!-- 달력 --></script>
	<script src="http://malsup.github.com/jquery.form.js"></script>

</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<title>Insert title here</title>

<form action="/board/saveImage" enctype="multipart/form-data" method="post">
	<input type="file" name="imgFile" />
	<input type="submit" value="이미지저장"/>
</form>


<input type="file" id="singleImg">

<input type="button" name="btn" value="저장하기">

<img id="single" src="">



<script>

var base64ToBlob = function(base64Data, contentType, sliceSize) {
    contentType = contentType || '';
    sliceSize = sliceSize || 512;

    var byteCharacters = atob(base64Data);
    var byteArrays = [];

    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
        var slice = byteCharacters.slice(offset, offset + sliceSize);

        var byteNumbers = new Array(slice.length);
        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        var byteArray = new Uint8Array(byteNumbers);
        byteArrays.push(byteArray);
    }

    var blob = new Blob(byteArrays, { type: contentType });
    console.log(blob);
    return blob;
}

$('#singleImg').on('change',singleImageSet);
		

function singleImageSet(e) {
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);
    
   
    filesArr.forEach(function(f) {
        if(!f.type.match("image.*")) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
        }
        sel_file = f;

        var reader = new FileReader();
        reader.onload = function(e) {
        			//싱글 응답 구성 세팅쪽 이미지
            		$("#single").attr("src", e.target.result);
                    
        			
        			
        	}
        	
        reader.readAsDataURL(f);
    });
}

$('input[name=btn]').on('click',function(e){
	var formD = new FormData();

	var importChk = $('#single').attr('src');
	console.log(importChk);

	var f = importChk;
	var mv = f.split(';');

	var mv_2 = mv[1].split(',');
	var contentType= mv[0].split(':')[1];
	var realData = mv_2[1];
	console.log(contentType);
	var singleBlob = base64ToBlob(realData, contentType);

	fileValue= $('#singleImg').val().split("\\");
	var singleFileName = fileValue[fileValue.length-1];
	//console.log(singleBlob);
	formD.append('singleImgFormId',$('#singleFormId').text());
	formD.append('single_input_img_data',singleBlob);
	formD.append('singleFileName',singleFileName);


	
		$.ajax({
			cache: false,
			url: "/board/response/update",
			processData:false,
			contentType:false,
			type:'POST',
			data:formD,
			success : function(data){
				//console.log(formD);
		    }, // success 

		    error : function(xhr, status) {
		      //  alert("이미지 전송 실패");
		    	
		    }
		})


})







</script>




</body>
</html>

