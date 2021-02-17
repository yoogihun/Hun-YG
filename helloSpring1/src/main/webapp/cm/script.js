var module = angular.module('myapp',[])
module.config(function($filterProvider){
	$filterProvider.register('capitalize',function(){
		return function(text){
			return text.charAt(0).toUpperCase()+text.slice(1);
		}
	})
})
var con = module.controller('myController',function($scope){
				$scope.boardlist=[
					{id:1,title:'제목',writer:"기훈",price:"10000",date:"1450100000000"},
					{id:2,title:'제목_2',writer:"유균",price:"10000",date:"1450100000000"}
				];
				
				$scope.text = "temp";
				
				$scope.remove = function(id){
					if(!id) return;
					var idx = -1;
					
					for(var i=0; i<$scope.boardlist.length; i++){
						if($scope.boardlist[i].id==id){
							idx = i;
							break;
						}
					}
					
					if(idx == -1) return;
					console.log(idx);
					$scope.boardlist.splice(idx,1);
				}
				
				
				$scope.boardMod = {};
				$scope.modBoard = function(id){
					var id = $scope.boardMod.id;
					var title = $scope.boardMod.title;
					var writer = $scope.boardMod.writer;
					console.log(id);
					console.log(title);
					console.log(writer);
					console.log($scope.boardlist[id-1]);
					$scope.boardlist[id-1].title=title;
					$scope.boardlist[id-1].writer=writer;
					
					
				}
				
				
				$scope.boardForm = {};
				$scope.addBoard = function(){
					var newId = ! $scope.boardlist.length ? 1 : $scope.boardlist[$scope.boardlist.length-1].id+1;
					var newItem = {
							id:newId,
							title:$scope.boardForm.title,
							writer:$scope.boardForm.writer
					};
					$scope.boardlist.push(newItem);
				}
				
				
				
				
				
});





/*angular.module('gihunApp',[]).controller('gihunCtn',function($scope){
	$scope.gihun = "hi gihun!";
	
	$scope.cat = function(){
		console.log("히히");
		alert("히히");
	}
})*/