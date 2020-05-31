<!DOCTYPE html> 
<html ng-app> 
<head> 
<title>AngularJS Sample</title> 
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script> </head> 
<script src="./script.js?ver=3"></script>
<body ng-init="num = 100">

<div ng-controller="YooCtn as ctl">
	<input type="text" ng-model="ctl.a">
	<input type="text" ng-model="num">

</div>

<div> <p>price:{{num}}</p> 
 
</div>
</body> 
</html>