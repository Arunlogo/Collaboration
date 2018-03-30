package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.Commentdao;
import com.niit.dao.Userdao;
import com.niit.model.Errorclazz;
import com.niit.model.User;
import com.niit.model.comments;

@RestController
public class Commetscontroller {
	@Autowired
	public Commentdao commentdao;
	@Autowired
	public Userdao userdao;
	@RequestMapping(value="/getallcomments/{id}",method=RequestMethod.GET)
	public ResponseEntity<?>getallcomments(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		List<comments>comments=commentdao.getallcoments(id);
		return new ResponseEntity<List<comments>>(comments,HttpStatus.OK);
		
	}
	@RequestMapping(value="/addcomment",method=RequestMethod.POST)
public ResponseEntity<?>addcomment(@RequestBody comments comment,HttpSession session){
		System.out.println("test"+comment.getCommentTxt());
	String email=(String)session.getAttribute("LoginId");
	/*String email="a@m.com";*/
	if(email==null) {
		Errorclazz error=new Errorclazz(6,"Please login");
		return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
	}
	try {
		User user=userdao.getuser(email);
		comment.setPostedon(new Date());
		comment.setPostedby(user);
		commentdao.addcomment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	catch(Exception e) {
		Errorclazz error=new Errorclazz(9,"Unable to post a comment try again later");
		return new ResponseEntity<Errorclazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
}
