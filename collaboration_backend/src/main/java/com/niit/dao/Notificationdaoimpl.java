package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Notification;
@Repository
@Transactional
public class Notificationdaoimpl implements Notidicationdao {
	@Autowired
private SessionFactory sessionFactory;
	public List<Notification> getallnotifications(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Notification where username=? and viewd=0");
		query.setString(0,email);
		
		List<Notification>notifications=query.list();
		
		return notifications;
	}

	public Notification getnotification(int id) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=(Notification) session.get(Notification.class, id);
		return notification;
	}

	public void updatenotification(int id) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=(Notification) session.get(Notification.class, id);
		notification.setViewd(true);
		session.update(notification);
		
	}

}
