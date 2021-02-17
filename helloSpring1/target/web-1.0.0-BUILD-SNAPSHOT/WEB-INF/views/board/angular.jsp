<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script> 
<script src="/cm/script.js"></script>

<title>AngularJS Sample</title> 




</head> 


<body>

<div ng-app="myapp" ng-controller="myController">
	<h1>로우 생성</h1>
	<form ng-submit="addBoard()">
		Title:<input type="text" ng-model="boardForm.title" autofocus></br>
		Name:<input type="text" ng-model="boardForm.writer"><br/>
		<button type="submit">작성</button>
	</form>
	
	<h1>로우 수정</h1>
	<form ng-submit="modBoard()">
		Id:<input type="text" ng-model="boardMod.id">
		Title:<input type="text" ng-model="boardMod.title" autofocus></br>
		Name:<input type="text" ng-model="boardMod.writer">
		<button type="submit">수정</button>
	</form>

	<table style="width:100%" border="1">
		<tr >
			<td>NO</td>
			<td>title</td>
			<td>Name</td>
			<td></td>
		</tr>
		<tr ng-repeat="res in boardlist">
			<td>{{$index+1}}</td>
			<td>{{res.title}}</td>
			<td>{{res.writer}}</td>
			<td>{{res.price|currency:'₩'}}</td>
			<td>{{res.date| date:'yyyy-MM-dd'}}</td>
			<td><input type="button" ng-click="remove(res.id)" value="삭제"></td>
		</tr>
	
	</table>
	<p>{{text|capitalize}}</p>
	
	
</div> 




<!-- 
<div ng-app="gihunApp" ng-controller="gihunCtn">
	<p>{{gihun}}</p>
	<input type="button" ng-click="cat()">

</div> -->

</body> 
</html>