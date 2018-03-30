package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Friend;
import com.niit.model.User;
@Repository
@Transactional
public class Frienddaoimpl implements Frienddao {
	@Autowired
private SessionFactory sessionFactory;
	public List<User> suggestedusers(String email) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("select * from User_Table where email in(select email from User_Table where email!=? minus (select fromId from Friend where toId=? union select toId from friend where fromId=?))and role!='ADMIN'");
		query.setString(0, email);
		query.setString(1, email);
		query.setString(2, email);
		query.addEntity(User.class);
		List<User>user=query.list();
		return user;
	}
	public void addfriend(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);
		
	}
	public List<Friend> getpendingreq(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(" from Friend where toId=? and status=?");
		query.setString(0, email);
		query.setCharacter(1, 'P');
		List<Friend> pendingreq=query.list();
		return pendingreq;
	}
	public void updatereq(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		
			session.update(friend);
	
		
	}
	public List<User> getallfriends(String email) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("select * from User_Table where email in(select fromId from friend where toId=? and status='A' union select toId from friend where fromId=? and status='A')");
		query.setString(0, email);
		query.setString(1, email);
		query.addEntity(User.class);
		List<User> user=query.list();
		return user;
	}

}
