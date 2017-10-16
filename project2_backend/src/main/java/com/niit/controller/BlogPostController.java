package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Error;
import com.niit.model.User;
import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;
import com.niit.model.Error;

@Controller
public class BlogPostController {
	@Autowired
	private BlogPostDao blogPostDao;
	@Autowired
	private UserDao userDao;
	@RequestMapping(value="/saveblogpost",method=RequestMethod.POST)

	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost,HttpSession session){

			if(session.getAttribute("username")==null) {
		Error error=new Error(5,"Unauthorised User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		User user=userDao.getUserByUsername(username);
		
		blogPost.setPostedOn(new Date());
		blogPost.setPostedBy(user);
		try {
			blogPostDao.saveBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e) { 
			Error error=new Error(6,"Unable to insert blogPost details"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/getblogposts/{approved}")
	public ResponseEntity<?> getAllBlogs(@PathVariable int approved,HttpSession session){
		if(session.getAttribute("username")==null){
	Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> blogPosts=blogPostDao.getAllBlogs(approved);
		return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	}
	@RequestMapping(value="/getblogpostbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPostById(@PathVariable int id,HttpSession session){
		if(session.getAttribute("username")==null){
	Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		BlogPost blogPost=blogPostDao.getBlogPostById(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
 @RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauthorized access");
					return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try {
		blogPostDao.updateBlogPost(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e) {
			Error error=new Error(6,"Unable to update Blog Post"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 @RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
 public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
	 if(session.getAttribute("username")==null){
		 Error error=new Error(5,"Unauthorised User");
		 return new ResponseEntity(error,HttpStatus.UNAUTHORIZED);
	 }
	 String username=(String)session.getAttribute("username");
	 User user=userDao.getUserByUsername(username);
	 blogComment.setCommentedBy(user);
	 blogComment.setCommentesOn(new Date());
	 try {
		 blogPostDao.addBlogComment(blogComment);
		 return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
	 }
	 catch(Exception e) {
		 Error error=new Error(7,"Unable to add Blog Comment"+e.getMessage());
		 return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		 	 
	 }
 }
 @RequestMapping(value="/getblogcomments/{blogPostId}")
 public ResponseEntity<?> getBlogComments(@PathVariable int blogPostId,HttpSession session){
	 if(session.getAttribute("username")==null){
		 Error error=new Error(5,"Unauthorised User");
		 return new ResponseEntity(error,HttpStatus.UNAUTHORIZED);
	 }
List<BlogComment> blogComments=blogPostDao.getAllBlogComments(blogPostId);
return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
 }
}
