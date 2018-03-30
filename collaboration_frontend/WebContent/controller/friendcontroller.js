/**
 * 
 */
app.controller("FriendController", function($scope, $location, $rootScope,
		FriendService) {
	function getallsuggetstedusers() {
		FriendService.suggestedusers().then(function(response) {
			console.log("friendcontroller")
			console.log(response.data);
			$scope.suggestedusers = response.data;
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401) {
				$location.path('/login')
			}
		})

	}
	$scope.addfriend = function(user) {
		FriendService.addfriend(user).then(function(response) {
			getallsuggetstedusers();
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401) {
				$location.path('/login')
			}
		})
	}
	function getpendingreq(){
	FriendService.getpendingreq().then(function(response) {
		
		$scope.friendreq = response.data
		console.log($scope.friendreq);
	}, function(reponse) {
		$rootScope.error = response.data
		if (response.status == 401) {
			$location.path('/login')
		}
	})
	}
	$scope.updatereq=function(friendReq,status){
		friendReq.status=status;
		FriendService.updatereq(friendReq).then(function(response){
			getpendingreq()
			$rootScope.friendreqCount=$rootScope.friendreqCount-1;
		},function(response){
			$rootScope.error = response.data
			if (response.status == 401) {
				$location.path('/login')
			}
		})
	}
	FriendService.getallfriends().then(function(response){
		$scope.friends=response.data
		console.log("friends"+$scope.friends)
	},function(response){
		$rootScope.error = response.data
		if (response.status == 401) {
			$location.path('/login')
		}
	})
	$scope.showuser=function(){
		$scope.show=!$scope.show;
	}
	

	getallsuggetstedusers()
	getpendingreq()

})