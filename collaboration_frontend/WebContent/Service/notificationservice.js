/**
 * 
 */
app.factory("NotificationService",function($http){
	var notificationService={}
	notificationService.getallnotifications=function(){
		return $http.get("http://localhost:8090/collaboration_middleware/getallnotifications")
	}
	notificationService.getnotification=function(id){
	return $http.get("http://localhost:8090/collaboration_middleware/getnotification/"+id);
	}
	notificationService.updatenotification=function(id){
		return $http.put("http://localhost:8090/collaboration_middleware/updatenotification/"+id);
	}
	return notificationService;
})