package com.niit.dao;

import java.util.List;

import com.niit.model.Notification;

public interface Notidicationdao {
public List<Notification>getallnotifications(String email);
public Notification getnotification(int id);
public void updatenotification(int id);

}
