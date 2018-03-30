package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Data;
import com.niit.model.Job;
import com.niit.model.User;

@Repository
@Transactional
public class UserdaoImpl implements Userdao {
@Autowired
private SessionFactory sessionFactory;
public boolean isemailvalid(String email) {
	Session session=sessionFactory.getCurrentSession();
	Query query=session.createQuery("from User where email=?");
	query.setString(0, email);
	User user=(User)query.uniqueResult();
	if(user==null) {
		return true;}
	else {
	return false;
	}
}

	public User UserRegistration(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
		return user;
		
		

	}

	public User Emaillogin(Data data) {
		Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("from User where email=? and password=?");
		query.setString(0,data.getInp());
		query.setString(1,data.getInp2());
		User user1=(User)query.uniqueResult();
	
		return user1;
	}

	public User PhoneNumberLogin(Data data) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where phoneNumber=? and password=?");
		query.setString(0,data.getInp());
		query.setString(1, data.getInp2());
		User user=(User)query.uniqueResult();
		return user;
	}

	public boolean isphonenumbervalid(String phonenumber) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where phoneNumber=?");
		query.setString(0, phonenumber);
		User user=(User)query.uniqueResult();
		if(user==null) {
			return true;}
		else {
		return false;
		}
	}

	public void update(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		
	}
	public User getuser(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User) session.get(User.class,email);
		return user;
	}

	public User getuser2(String phoneNumber) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User) session.get(User.class,phoneNumber);
		return user;	}

	public void deleteAdmin(String email) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println("test"+email);
		User user=(User) session.get(User.class,email);
		System.out.println("test"+user);
		session.delete(user);
	}

	public List<User> getAllAdmin() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where role=?");
		query.setString(0, "ADMIN");
		List<User>admin=query.list();
		return admin;
	}

	
}
