/**
 * 
 */
app.controller("NotificationControlelr",function($scope,$rootScope,NotificationService,$location,$routeParams,FriendService){
	var id=$routeParams.id
	
	if(id!=undefined){
		NotificationService.getnotification(id).then(function(response){
			$scope.notification=response.data
		},function(response){
			$rootScope.error=response.data;
			if(response.status==401)
				$location.path('/home')
			
		})
		NotificationService.updatenotification(id).then(function(response){
		/*	getallnotifications()*/
			$rootScope.notificationsCount=$rootScope.notificationsCount-1
		},function(response){
			$rootScope.error=response.data;
			if(response.status==401)
				$location.path('/home')
		})
		
	}
	function getallnotifications(){
		NotificationService.getallnotifications().then (function(response){
			console.log(response.data)
			$rootScope.notifications=response.data
			$rootScope.notificationsCount=$rootScope.notifications.length
			console.log($rootScope.notificationsCount)
		},function(response){
			$rootScope.error=response.data;
			if(response.status==401)
				$location.path('/home')
			
		})
	}
	function getpendingreq(){
		FriendService.getpendingreq().then(function(response) {
			
			$rootScope.friendreq = response.data
			$rootScope.friendreqCount=$rootScope.friendreq.length
			console.log("notifycntr"+$scope.friendreq);
		}, function(reponse) {
			$rootScope.error = response.data
			if (response.status == 401) {
				$location.path('/login')
			}
		})
		}
	getallnotifications()
	getpendingreq()
})