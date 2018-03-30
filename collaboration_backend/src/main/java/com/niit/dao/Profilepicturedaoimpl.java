package com.niit.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Profilepic;
@Repository
@Transactional
public class Profilepicturedaoimpl implements Profilepicturedao{
	@Autowired
private SessionFactory sessionFactory;
	public void uploadpic(Profilepic profilepic) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilepic);
		
	}

	public Profilepic getpic(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Profilepic where email=?");
		query.setString(0, email);
		Profilepic pic=(Profilepic) query.uniqueResult();
		return pic;
	}

}
