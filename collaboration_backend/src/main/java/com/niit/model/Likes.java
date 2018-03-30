package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Likes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@ManyToOne
	private Blogs blogs;
	@ManyToOne
	private User user;
public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Blogs getBlogs() {
		return blogs;
	}
	public void setBlogs(Blogs blogs) {
		this.blogs = blogs;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
