/**
 * 
 */
app.factory('JobService',function($http){
	var jobService={}
	jobService.getalljobs=function(){
		return $http.get("http://localhost:8090/collaboration_middleware/getalljobs")
	}
	jobService.addjob=function(job){
		return $http.post("http://localhost:8090/collaboration_middleware/addjob",job)
	}
	jobService.getjob=function(id){
		return $http.get("http://localhost:8090/collaboration_middleware/getjob/"+id)
	}
	return jobService;
})