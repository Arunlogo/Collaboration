/**
 * 
 */
app.controller('usercontroller',function(UserService,$scope,$location,$routeParams, $rootScope,$cookieStore){
	if($rootScope.LoggedUserdata!=undefined){
		
		UserService.getuserdetails().then(function(response){
			$scope.user=response.data
			console.log(response.data)
		}),function(response){
			$scope.error=response.data
			$location.path('/login')
		}
	}
	$scope.register=function(){
		console.log($scope.user.firstName)
		UserService.register($scope.user).then(function(response){
			alert("Registered successfully")
			$location.path('/login')
			console.log(response.data)
		},function(response){
			console.log(response.data)
		})
	}
	$scope.login=function(){
		console.log($scope.inp);
		console.log($scope.inp2);

		
		if(isNaN($scope.inp)){
			console.log("not integer")
     		UserService.EmailLogin($scope.inp,$scope.inp2).then(function(response){
     			console.log(response.data);
     			
     			$rootScope.LoggedUserdata=response.data;
     			$cookieStore.put('LoggedUserdata',response.data)
			$location.path('/home')
		},function(response){
			$scope.errormessage=response.data;
			
			$location.path('/login')
			alert("Enter valid credentials")
			console.log(response.data);
			console.log(response.status);
			
		})
		}
		else{
			UserService.PhoneNumberLogin($scope.inp,$scope.inp2).then(function(response){
				$rootScope.LoggedUserdata=response.data;
				$cookieStore.put('LoggedUserdata',response.data)
				$location.path('/home')
			},function(response){
				$scope.errormessage=response.data;
				console.log(response.data);
				console.log(response.status);
			})
		}
	}
	$scope.editprofile=function(){
		UserService.editprofile($scope.user).then(function(response){
			alert("Profile udated successfully")
			$rootScope.LoggedUserdata=response.data;
			$cookieStore.put('LoggedUserdata',response.data)
			$location.path('/home')
		},function(response){
			$scope.errormessage=response.data;
			console.log(response.data)
			
		})
	}
	$scope.uploadFile=function(){
		var file= $scope.myFile;
		
		console.log(file);
		UserService.uploadpic(file).then(function(response){
			console.log("img uploaded");
		},function(response){
			$rootScope.error=response.data
			if(response.status==401){
			$location.path('/login')
			}
		})
	}
	
})