package com.niit.dao;

import java.util.List;

import com.niit.model.Blogs;

public interface Blogdao {
public void addblog(Blogs blog);
public List<Blogs>getblogs(boolean approved);
public Blogs getblog(int id);
public void blogapproved(int id);
public void blogrejected(int id,String reason);
public void deleteblog(int id);
}
