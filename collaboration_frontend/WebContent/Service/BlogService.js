/**
 * 
 */
app.factory('BlogService',function($http){
	var blogService={}
	blogService.addblog=function( blog){
		return $http.post("http://localhost:8090/collaboration_middleware/addblog",blog)
	}
	blogService.waitingforapprovel=function(){
		return $http.get("http://localhost:8090/collaboration_middleware/getblogs/"+false)
	}
	blogService.approvedblogs=function(){
		return  $http.get("http://localhost:8090/collaboration_middleware/getblogs/"+true)
	}
	blogService.getblog=function(id){
		return  $http.get("http://localhost:8090/collaboration_middleware/getblog/"+id)
	}
	blogService.hasuserliked=function(id){
		return  $http.get("http://localhost:8090/collaboration_middleware/hasuserliked/"+id)
	}
	blogService.updatelikes=function(id)
	{
		return  $http.put("http://localhost:8090/collaboration_middleware/updatelikes/"+id)
	}
	blogService.hasuserdisliked=function(id){
		return  $http.get("http://localhost:8090/collaboration_middleware/hasuserdislikes/"+id)
		}
	blogService.updatedislikes=function(id){
		return $http.put("http://localhost:8090/collaboration_middleware/updatedislikes/"+id)
	}
	blogService.blogApproved=function(id){
		return $http.put("http://localhost:8090/collaboration_middleware/blogapproved/"+id)
	}
	blogService.blogrejected=function(id,rejectionreason){
		return $http.put("http://localhost:8090/collaboration_middleware/blogrejected/"+id+"/"+rejectionreason)
	}
	blogService.deleteblog=function(id){
		return $http.delete("http://localhost:8090/collaboration_middleware/deleteblog/"+id)
	}
	blogService.gettallcomments=function(id){
		return $http.get("http://localhost:8090/collaboration_middleware/getallcomments/"+id)
	}
	blogService.addcomment=function(comment){
		return $http.post("http://localhost:8090/collaboration_middleware/addcomment",comment)
	}
	
	return blogService;
})