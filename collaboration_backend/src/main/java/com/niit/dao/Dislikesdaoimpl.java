package com.niit.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blogs;
import com.niit.model.Dislikes;
import com.niit.model.User;
@Repository
@Transactional
public class Dislikesdaoimpl implements Dislikesdao {
	@Autowired
private SessionFactory sessionFactory;
	
	
	public Dislikes hasuserdisliked(int id, String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Dislikes where blogs.id=? and user.email=?");
		query.setInteger(0, id);
		query.setString(1, email);
		Dislikes dislikes=(Dislikes)query.uniqueResult();
		return dislikes;
	}

	public Blogs updatedislikes(int id, String email) {
		Session session=sessionFactory.getCurrentSession();
		Blogs blogs=(Blogs)session.get(Blogs.class, id);
		System.out.println(blogs.getId());
		User user=(User)session.get(User.class, email);
		Dislikes dislikes=hasuserdisliked(id,email);
		if(dislikes==null) {
			Dislikes dislikes1=new Dislikes();
			dislikes1.setBlogs(blogs);
			dislikes1.setUser(user);
			session.save(dislikes1);
			blogs.setDislikes(blogs.getDislikes()+1);
			session.update(blogs);
			
		}
		else {
			session.delete(dislikes);
			blogs.setDislikes(blogs.getDislikes()-1);
			session.update(blogs);
		
		}
		return blogs;
	}

}
