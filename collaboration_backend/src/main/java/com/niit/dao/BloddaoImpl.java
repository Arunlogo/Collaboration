package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blogs;
import com.niit.model.Notification;
@Repository
@Transactional
public class BloddaoImpl implements Blogdao {
	@Autowired
private SessionFactory sessionFactory;
	public void addblog(Blogs blog) {
		Session session=sessionFactory.getCurrentSession();
	session.save(blog);
		
	}
	public List<Blogs> getblogs(boolean approved) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blogs where approved="+approved);
		List<Blogs>getblogs=query.list();
		return getblogs;
	}
	public Blogs getblog(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blogs blog=(Blogs)session.get(Blogs.class, id);
		blog.setViews(blog.getViews()+1);
	session.update(blog);
		return blog;
	}
	public void blogapproved(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blogs blog=(Blogs)session.get(Blogs.class,id);
		Notification notification=new Notification();
		notification.setBlogTitle(blog.getBlogTitle());
		notification.setUsername(blog.getPostedBy().getEmail());
		notification.setApprovalStatus("Approved");
		session.save(notification);
		blog.setApproved(true);
		session.update(blog);
	}
	public void blogrejected(int id,String reason) {
		Session session=sessionFactory.getCurrentSession();
		Blogs blog=(Blogs)session.get(Blogs.class,id);
		Notification notification=new Notification();
		notification.setBlogTitle(blog.getBlogTitle());
		notification.setUsername(blog.getPostedBy().getEmail());
		notification.setApprovalStatus("Rejected");
		notification.setRejectionreason(reason);
		session.save(notification);
		session.delete(blog);
	}
	public void deleteblog(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blogs blog=(Blogs)session.get(Blogs.class, id);
		session.delete(blog);
		
	}
	
}
