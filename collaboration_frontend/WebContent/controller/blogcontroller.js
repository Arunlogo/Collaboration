/**
 * 
 */
app.controller('Blogcontroller',function($scope,BlogService,$rootScope,$location){
	$scope.addblog=function(){
		BlogService.addblog($scope.blog).then(function(response){
			console.log($scope.blog);
			alert("Blogs Posted Successfully...Waiting for approval")
			console.log(response.data);
		},function(response){
			$rootScope.error=response.data
			$location.path('/login')
		})
	}
	if($rootScope.LoggedUserdata.role=='ADMIN'){
	BlogService.waitingforapprovel().then(function(response){
		$scope.waitingblog=response.data;
	},function(response){
		$rootScope.error=response.data
		if(response.status==401){
		$location.path('/login')
		}
	})}
	BlogService.approvedblogs().then(function(response){
		$scope.approvedblog=response.data;
	},function(response){
		$rootScope.error=response.data
		$location.path('/login')
	})
})