package com.niit.dao;

import java.util.List;

import com.niit.model.Job;

public interface Jobdao {
public void addJob(Job job);
public List<Job> getalljobs();
public Job getjob(int id);
}
