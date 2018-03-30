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
import com.niit.dao.Likesdao;
import com.niit.model.Blogs;
import com.niit.model.Errorclazz;
import com.niit.model.Likes;

@RestController
public class Likecontroller {
	@Autowired
	Likesdao likesdao;
	@Autowired
	Blogdao blogdao;
	@RequestMapping(value="/hasuserliked/{id}",method=RequestMethod.GET)
	public ResponseEntity<?>hasuserLiked(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
	Likes likes=likesdao.hasuserLiked(id, email);
	return new ResponseEntity<Likes>(likes,HttpStatus.OK);
	}
	@RequestMapping(value="/updatelikes/{id}",method=RequestMethod.PUT)
 public ResponseEntity<?>updatelikes(@PathVariable int id,HttpSession session){
	 String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		Blogs blogs=likesdao.updateLikes(id, email);
		return new ResponseEntity<Blogs>(blogs,HttpStatus.OK);
 }
}
