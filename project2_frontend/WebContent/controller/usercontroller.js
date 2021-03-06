/**
 * UserController
 */
ngApp.controller('UserController',function($scope,$location,UserService,$rootScope,$cookieStore){
	$scope.registerUser=function(){
		console.log($scope.user)
		
		UserService.registerUser($scope.user).then(function(response){
			console.log(response.status)
			$scope.success="registered successfully...please login again"
			$location.path('/login')
		},function(response){
			
			console.log(response.status)
			console.log(response.data)
			$scope.error=response.data
			if($scope.error.code==2)
			$scope.duplicateEmail=response.data
			if($scope.error.code==3)
			$scope.duplicateUsername=response.data
			if($scope.error.code==1)
			$scope.exception=response.data
			$location.path('/registration')
			
		
		})
	}
	$scope.login=function(){
		console.log($scope.user)
		UserService.login($scope.user).then(function(response){
			console.log(response.data)
			$rootScope.currentUser=response.data
			$cookieStore.put('currentUser',response.data)
			$location.path('/home')
		},function(response){
			console.log(response.data)
			$scope.loginFail=response.data
			$location.path('/login')
			
		})
			
		
	}
	if($rootScope.currentUser!=undefined){
	UserService.getUser().then(function(response){
		$scope.user=response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
		})
	}
		$scope.updateUser=function(){
		UserService.updateUser($scope.user).then(function(response){
			alert('update the details successfully')
			$location.path('/home')
		},function(response){
			if(response.status==401)
				$location.path('/login')
				
			$scope.error=response.data
			$location.path=('/editprofile')
			
		
			
		
	})
	}
})