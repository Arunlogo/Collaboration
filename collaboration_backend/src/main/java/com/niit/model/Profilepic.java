package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class Profilepic {
	@Id
private String email;
	@Lob
private byte[] img;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
}
