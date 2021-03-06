package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao {
	@Autowired
	private SessionFactory sessionFactory;
		public void saveBlogPost(BlogPost blogPost) {
			Session session=sessionFactory.getCurrentSession();
			session.save(blogPost);

		}
		public List<BlogPost> getAllBlogs(int approved) {
			
				Session session=sessionFactory.getCurrentSession();
				
				
				Query query=session.createQuery("from BlogPost where approved="+approved);
				return query.list();
			}
		public BlogPost getBlogPostById(int id) {
			Session session=sessionFactory.getCurrentSession();
			BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
			return blogPost;
		}
		public void updateBlogPost(BlogPost blogPost) {
			Session session=sessionFactory.getCurrentSession();
			System.out.println(blogPost.isApproved());
			session.update(blogPost);
			
		}
		public void addBlogComment(BlogComment blogComment) {
			Session session=sessionFactory.getCurrentSession();
			session.save(blogComment);
			
		}
		public List<BlogComment> getAllBlogComments(int blogPostId) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from BlogComment where blogPost.id=?");
			query.setInteger(0, blogPostId);
			return query.list();
		}
		}	

