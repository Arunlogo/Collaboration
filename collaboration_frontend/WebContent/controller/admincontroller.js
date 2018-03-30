/**
 * 
 */
app.controller("AdminController" ,function($scope,UserService,$location,$rootScope){
	$scope.register=function(){
		$scope.user.role='ADMIN';
		console.log($scope.user.firstName)
		UserService.register($scope.user).then(function(response){
			alert("Added successfully")
			console.log(response.data)
			$location.path('/getAllAdmin')
		},function(response){
			alert(response.data)
			console.log(response.data)
		})
	}
	function getalladmin(){
	UserService.getalladmin().then(function(response){
		$scope.admin=response.data;
		console.log(response.data);
	},function(response){
		$rootScope.error=response.data
		$location.path('/login')
	
	})
	}
	$scope.deleteadmin=function(email){
		UserService.deleteadmin(email).then(function(response){
			console.log("deleted");
			getalladmin();
		},function(response){
			$rootScope.error=response.data
			$location.path('/login')
		})
	}
	getalladmin();
})