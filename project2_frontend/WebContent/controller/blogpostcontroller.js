
/**
 * BlogPostController
 */
ngApp.controller('BlogPostController',function($scope,BlogPostService,$location){
	BlogPostService.getBlogPostsWaitingForApproval().then(function(response){
				$scope.blogPostsWaitingForApproval=response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	      BlogPostService.getBlogPostsApproved().then(function(response){ 
	    	 
	    	  $scope.blogPostsApproved=response.data;
	      },function(response){
	    	  
	    	  if(response.status==401)
	    		  $location.path('/login')
	      })
	      
	      $scope.addBlogPost=function(){
	BlogPostService.addBlogPost($scope.blogPost).then(function(response){
		console.log(response.status)
		alert('Blogpost added successfully and waiting for approval ')
		$location.path('/getallblogs')
	},function(response){
		if(response.status==401)
			$location.path('/login')
		else
			$location.path('/saveblogpost')
	
	})
	      
}
})
	