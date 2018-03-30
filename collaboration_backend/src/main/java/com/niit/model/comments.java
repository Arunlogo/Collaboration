package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
@Entity
public class comments {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@ManyToOne
private Blogs blog;
@Lob
private String commentTxt;
@ManyToOne
private User postedby;
private Date postedon;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Blogs getBlog() {
	return blog;
}
public void setBlog(Blogs blog) {
	this.blog = blog;
}

public String getCommentTxt() {
	return commentTxt;
}
public void setCommentTxt(String commentTxt) {
	this.commentTxt = commentTxt;
}
public User getPostedby() {
	return postedby;
}
public void setPostedby(User postedby) {
	this.postedby = postedby;
}
public Date getPostedon() {
	return postedon;
}
public void setPostedon(Date postedon) {
	this.postedon = postedon;
}


}
