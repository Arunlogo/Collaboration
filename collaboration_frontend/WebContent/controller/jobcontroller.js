/**
 * 
 */
app.controller('jobcontroller',function(JobService,$scope,$location,$rootScope,$routeParams){
	var id=$routeParams.id
	console.log(id);
	if(id!=undefined){
		JobService.getjob(id).then(function(response){
			console.log(response.data)
			$scope.job=response.data;
		},function(response){
			$scope.error=response.data;
		})
	}
	function getalljobs(){
		JobService.getalljobs().then(function(response){
		
		$scope.jobs=response.data;
	},function(response){
		$rootScope.error=response.data;
		$location.path('/home')
	})}
	
	
	$scope.addjob=function(){
		console.log($scope.job);
		JobService.addjob($scope.job).then(function(response){
			alert('job details added successfully');
			$location.path('/home')
		},function(response){
			console.log(response.data);
			$rootScope.error=response.data;
			if(response.status==401)
				$location.path('/home')
			
		})
	}
	getalljobs()
})