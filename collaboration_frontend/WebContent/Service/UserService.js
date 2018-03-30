/**
 * 
 */
app.factory('UserService',function($http){
	var userService={}
	userService.register=function(user){
		return $http.post("http://localhost:8090/collaboration_middleware/userRegistration",user)
	}
	userService.EmailLogin=function(inp,inp2){
		var data={'inp':inp,'inp2':inp2};
		
		return $http.post("http://localhost:8090/collaboration_middleware/EmailLogin",data)
	}
userService.PhoneNumberLogin=function(inp,inp2){
	var data={'inp':inp,'inp2':inp2};
		
		return $http.post("http://localhost:8090/collaboration_middleware/PhonenumberLogin",data)
	}
userService.Emaillogout=function(){
	return $http.put("http://localhost:8090/collaboration_middleware/EmailLogout")
}
userService.Phonenumberlogout=function(){
	return $http.put("http://localhost:8090/collaboration_middleware/PhonenumberLogout")
}
userService.getuserdetails=function(){
	return $http.get("http://localhost:8090/collaboration_middleware/getuser")
}
userService.editprofile=function(user){
	return $http.post("http://localhost:8090/collaboration_middleware/edit",user)
}
userService.getalladmin=function(){
	return $http.get("http://localhost:8090/collaboration_middleware/gettAllAdmin")
}
userService.deleteadmin=function(email){
	console.log(email);
	return $http.delete("http://localhost:8090/collaboration_middleware/deleteAdmin/"+email)
}
userService.uploadpic=function(file){
	console.log(file)
	return $http.post("http://localhost:8090/collaboration_middleware/uploadpic",file)
}
	return userService;
})