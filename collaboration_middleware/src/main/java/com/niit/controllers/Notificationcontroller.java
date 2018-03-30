package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.Notidicationdao;
import com.niit.model.Errorclazz;
import com.niit.model.Notification;

@RestController
public class Notificationcontroller {
	@Autowired
	Notidicationdao notificationdao;
	@RequestMapping(value="/getallnotifications",method=RequestMethod.GET)
	public ResponseEntity<?>getallnotification(HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Notification> notifications=notificationdao.getallnotifications(email);
		return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
	}
	@RequestMapping(value="/getnotification/{id}",method=RequestMethod.GET)
public ResponseEntity<?>getnotification(@PathVariable int id,HttpSession session){
	String email=(String)session.getAttribute("LoginId");
	/*String email="a@m.com";*/
	if(email==null) {
		Errorclazz error=new Errorclazz(6,"Please login");
		return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
	}
	Notification notification=notificationdao.getnotification(id);
	return new ResponseEntity<Notification>(notification,HttpStatus.OK);
}
	@RequestMapping(value="/updatenotification/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?>updatenotification(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		notificationdao.updatenotification(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
