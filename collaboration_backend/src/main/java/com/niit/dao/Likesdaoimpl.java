package com.niit.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blogs;
import com.niit.model.Likes;
import com.niit.model.User;
@Repository
@Transactional
public class Likesdaoimpl implements Likesdao {
	@Autowired
private SessionFactory sessionFactory;
	public Likes hasuserLiked(int postId, String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Likes where blogs.id=? and user.email=?");
		query.setInteger(0, postId);
		query.setString(1, email);
		Likes liked=(Likes)query.uniqueResult(); 
		
		return liked;
	}

	public Blogs updateLikes(int postId, String email) {
		Session session=sessionFactory.getCurrentSession();
		Likes liked=(Likes)hasuserLiked(postId,email);
		Blogs blog=(Blogs)session.get(Blogs.class, postId);
		User user=(User)session.get(User.class, email);
		if(liked==null) {
			Likes like=new Likes();
			like.setBlogs(blog);
			like.setUser(user);
			session.save(like);
			blog.setLikes(blog.getLikes()+1);
			session.update(blog);
			
			
		}
		else {
			session.delete(liked);
			blog.setLikes(blog.getLikes()-1);
			session.update(blog);
			
		}
		
		return blog;
	}

}
