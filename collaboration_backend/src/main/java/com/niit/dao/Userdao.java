package com.niit.dao;

import java.util.List;

import com.niit.model.Data;
import com.niit.model.Job;
import com.niit.model.User;

public interface Userdao {
public User UserRegistration(User user);
public boolean isemailvalid(String email);
public User Emaillogin(Data data);
public User PhoneNumberLogin(Data user);
public boolean isphonenumbervalid(String phonenumber);
public void update(User user);

public User getuser(String email);
public User getuser2(String phoneNumber);
public void deleteAdmin(String email);
public List<User> getAllAdmin();

}