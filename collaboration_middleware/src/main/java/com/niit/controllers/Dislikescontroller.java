package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.Blogdao;
import com.niit.dao.Dislikesdao;
import com.niit.model.Blogs;
import com.niit.model.Dislikes;
import com.niit.model.Errorclazz;

@RestController
public class Dislikescontroller {
	@Autowired
	Dislikesdao dislikesdao;
	Blogdao blogdao;
	@RequestMapping(value="/hasuserdislikes/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> hasuserdisliked(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		Dislikes dislikes=dislikesdao.hasuserdisliked(id, email);
		return new ResponseEntity<Dislikes>(dislikes,HttpStatus.OK);
	}
	@RequestMapping(value="/updatedislikes/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?>updatedislikes(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		Blogs blogs=(Blogs)dislikesdao.updatedislikes(id, email);
		return new ResponseEntity<Blogs>(blogs,HttpStatus.OK);
	}

}
