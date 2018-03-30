package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Job;
@Repository
@Transactional
public class Jobdaoimpl implements Jobdao {
@Autowired
private SessionFactory sessionFactory; 
	public void addJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
	}
	public List<Job> getalljobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job");
	List <Job>jobs=query.list();
	return jobs;
	}
	public Job getjob(int id) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class, id);
		return job;
	}

}
