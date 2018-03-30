/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={}
	friendService.suggestedusers=function(){
		return $http.get("http://localhost:8090/collaboration_middleware/suggestedusers")
	}
	friendService.addfriend=function(user){
		return $http.post("http://localhost:8090/collaboration_middleware/addfriend",user)
	}
	friendService.getpendingreq=function(){
		return $http.get("http://localhost:8090/collaboration_middleware/getpendingreq")
	}
	friendService.updatereq=function(friendReq){
		return $http.post("http://localhost:8090/collaboration_middleware/updatereq",friendReq)
	}
	friendService.getallfriends=function(){
		return $http.get("http://localhost:8090/collaboration_middleware/getallfriends")
	}
	return friendService;
})