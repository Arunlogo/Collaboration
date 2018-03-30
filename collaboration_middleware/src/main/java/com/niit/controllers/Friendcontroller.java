package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.Frienddao;
import com.niit.model.Errorclazz;
import com.niit.model.Friend;
import com.niit.model.User;
@RestController
public class Friendcontroller {
	@Autowired
public Frienddao frienddao;
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?>suggestedusers(HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
	
		List<User>user=frienddao.suggestedusers(email);
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/addfriend",method=RequestMethod.POST)
	public ResponseEntity<?>addfriend(HttpSession session,@RequestBody User user){

		String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		Friend friend=new Friend();
		friend.setFromId(email);
		friend.setToId(user.getEmail());
		friend.setStatus('P');
		frienddao.addfriend(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/getpendingreq",method=RequestMethod.GET)
	public ResponseEntity<?>getpendingreq(HttpSession session){

		String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Friend> pendingreq=frienddao.getpendingreq(email);
		
		return new ResponseEntity<List<Friend>>(pendingreq,HttpStatus.OK);
	}
	@RequestMapping(value="/updatereq",method=RequestMethod.POST)
	public ResponseEntity<?>updatereq(@RequestBody Friend friend,HttpSession session){
String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		frienddao.updatereq(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
	
	}
	@RequestMapping(value="/getallfriends",method=RequestMethod.GET)
	public ResponseEntity<?>getfriends(HttpSession session){
String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}	
		List<User> user=frienddao.getallfriends(email);
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
		
	}
}

