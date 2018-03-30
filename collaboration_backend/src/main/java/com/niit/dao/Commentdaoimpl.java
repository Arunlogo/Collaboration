package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.comments;
@Repository
@Transactional
public class Commentdaoimpl implements Commentdao {
	@Autowired
private SessionFactory sessionFactory;
	public List<comments> getallcoments(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from comments where blog.id=?");
		query.setInteger(0, id);
		List<comments>comments=query.list();
		return comments;
	}

	public void addcomment(comments comment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(comment);

	}

}
