/**
 * 
 */
var app=angular.module("app",['ngRoute','ngCookies'])
app.directive("fileinput", [function() {
	    return {
	        scope: {
	          fileinput: "=",
	          filepreview: "="
	        },
	        link: function(scope, element, attributes) {
	          element.bind("change", function(changeEvent) {
	            scope.fileinput = changeEvent.target.files[0];
	            var reader = new FileReader();
	            reader.onload = function(loadEvent) {
	              scope.$apply(function() {
	                scope.filepreview = loadEvent.target.result;
	              });
	            }
	            reader.readAsDataURL(scope.fileinput);
	          });
	        }
	      }
	    }]);
/*('demoFileModel', function ($parse) {
		        return {
		            restrict: 'A', //the directive can be used as an attribute only

		            
		             link is a function that defines functionality of directive
		             scope: scope associated with the element
		             element: element on which this directive used
		             attrs: key value pair of element attributes
		             
		            link: function (scope, element, attrs) {
		                var model = $parse(attrs.demoFileModel),
		                    modelSetter = model.assign; //define a setter for demoFileModel

		                //Bind change event on the element
		                element.bind('change', function () {
		                    //Call apply on scope, it checks for value changes and reflect them on UI
		                    scope.$apply(function () {
		                        //set the model value
		                        modelSetter(scope, element[0].files[0]);
		                    });
		                });
		            }
		        };
		    });*/
app.config(function($routeProvider){
	$routeProvider
	.when('/register',{templateUrl:'views/registrationform.html',controller:'usercontroller'})
	.when('/login',{templateUrl:'views/login.html',controller:'usercontroller'})
	.when('/addjob',{templateUrl:'views/Jobform.html',controller:'jobcontroller'})
	.when('/editprofile',{templateUrl:'views/editform.html',controller:'usercontroller'})
	.when('/getalljobs',{templateUrl:'views/joblist.html',controller:'jobcontroller'})
	.when('/getjob/:id',{templateUrl:'views/jobdetail.html',controller:'jobcontroller'})
	.when('/addblog',{templateUrl:'views/blogform.html',controller:'Blogcontroller'})
	.when('/getblogs',{templateUrl:'views/bloglist.html',controller:'Blogcontroller'})
	.when('/getblog/:id',{templateUrl:'views/blogdetails.html',controller:'Blogdetailscontroller'})
	.when('/getapprovalform/:id',{templateUrl:'views/approvalform.html',controller:'Blogdetailscontroller'})
	.when('/home',{templateUrl:"views/home.html",controller:'NotificationControlelr'})
	.when('/notificationdetails/:id',{templateUrl:"views/notificationdetails.html",controller:'NotificationControlelr'})
	.when('/addAdmin',{templateUrl:"views/adminform.html",controller:'AdminController'})
	.when('/getAllAdmin',{templateUrl:"views/adminlist.html",controller:'AdminController'})
	/*.when('/suggesteduser',{templateUrl:"views/userlist.html",controller:'FriendController'})
	.when('/pendingreq',{templateUrl:"views/friendrequest.html",controller:'FriendController'})*/
	.when('/friends',{templateUrl:"views/friends.html",controller:'FriendController'})
	.when('/friendlist',{templateUrl:"views/friendlist.html",controller:'FriendController'})
	.when('/chat',{templateUrl:"views/chat.html",controller:'ChatController'})
	.when('/profilepic',{templateUrl:'views/uploadprofilepic.html'})
		

	.otherwise({templateUrl:'views/login.html',controller:'usercontroller'})
	
})

app.run(function($cookieStore,$rootScope,$location,UserService){
		if($rootScope.LoggedUserdata==undefined){
			$rootScope.LoggedUserdata=$cookieStore.get('LoggedUserdata')
			console.log($rootScope.LoggedUserdata);
		}if(isNaN($rootScope.LoggedUserdata)){
		$rootScope.logout=function(){
			UserService.Emaillogout().then(function(response){
				delete $rootScope.LoggedUserdata
				$cookieStore.remove("LoggedUserdata")
				$rootScope.message="loggedout successfully";
				/*alert("loggedout successfully");*/
				$location.path('/login')
			},function(response){
				$rootScope.message="Please Login"
					$location.path('/login')
				console.log("please login")
			})
		}}
		else{
			$rootScope.logout=function(){
				console.log($rootScope.LoggedUserdata);
			UserService.Phonenumberlogout().then(function(response){
				delete $rootScope.LoggedUserdata
				$cookieStore.remove("LoggedUserdata")
				$rootScope.message="loggedout successfully";
			/*	alert("loggedout successfully");*/
				$location.path('/login')
			},function(response){
				$rootScope.message="Please Login"
					$location.path('/login')
				console.log("please login")
			})
		}}
		
	})