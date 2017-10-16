/**
 * blogpostservice
 */

ngApp.factory('BlogPostService',function($http){
	var blogPostService={}
	
	var BASE_URL="http://localhost:8090/project2_backend"
		
		blogPostService.addBlogPost=function(blogPost){
		return $http.post(BASE_URL+ "/saveblogpost",blogPost)
	}
	
	blogPostService.getBlogPostsWaitingForApproval=function(){
		return $http.get(BASE_URL + "/getblogposts/"+0)
	}
	
	blogPostService.getBlogPostsApproved=function(){
		return $http.get(BASE_URL + "/getblogposts/"+1)
	}
	
	blogPostService.getBlogPostById=function(id){
		return $http.get(BASE_URL + "/getblogpostbyid/"+id);
	}
	blogPostService.updateBlogPost=function(blogPost){
		return $http.put(BASE_URL + "/updateblogpost",blogPost);
	}
	
	blogPostService.addComment=function(blogComment){
		console.log(blogComment)
		return $http.post(BASE_URL + "/addblogcomment",blogComment);
	}
	
	blogPostService.getBlogComments=function(blogPostId){
		return $http.get(BASE_URL + "/getblogcomments/"+blogPostId);
		
	}
	return blogPostService;
})