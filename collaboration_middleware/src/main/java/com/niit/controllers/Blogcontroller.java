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

import com.niit.dao.Blogdao;
import com.niit.dao.Userdao;
import com.niit.model.Blogs;
import com.niit.model.Errorclazz;
import com.niit.model.User;

@RestController
public class Blogcontroller {
	@Autowired
	private Blogdao blogdao;
	@Autowired
	private Userdao userdao;
	@RequestMapping(value="/addblog",method=RequestMethod.POST)
	public ResponseEntity<?>addblog(@RequestBody Blogs blog,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		blog.setPostedon(new Date());
		User user=userdao.getuser(email);
		blog.setPostedBy(user);
		try {
			blogdao.addblog(blog);
			return new ResponseEntity<Blogs>(blog,HttpStatus.OK);
		}catch(Exception e) {
			Errorclazz error=new Errorclazz(6,"Unable to add blog");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	@RequestMapping(value="/getblogs/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?>getblogs(@PathVariable boolean approved,HttpSession session){

		String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}/*String email="a@m.com";*/
		if(!approved) {
			User user=userdao.getuser(email);
			if(!user.getRole().equals("ADMIN")) {
				Errorclazz error=new Errorclazz(8,"Access Denied");
				return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
			}
		}
		List<Blogs>getblogs=blogdao.getblogs(approved);
		return new ResponseEntity<List<Blogs>>(getblogs,HttpStatus.OK);
		
	}
	@RequestMapping(value="/getblog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?>getblog(@PathVariable int id,HttpSession session){
String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		Blogs blog=blogdao.getblog(id);

		if(!blog.isApproved()) {
			User user=userdao.getuser(email);
			if(!user.getRole().equals("ADMIN")) {
				Errorclazz error=new Errorclazz(8,"Access Denied");
				return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
			}
		}
		return new ResponseEntity<Blogs>(blog,HttpStatus.OK);
	}
	@RequestMapping(value="/blogapproved/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?>blogApproved(@PathVariable int id,HttpSession session){
String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userdao.getuser(email);
		if(!user.getRole().equals("ADMIN")) {
			Errorclazz error=new Errorclazz(8,"Access Denied");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		blogdao.blogapproved(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/blogrejected/{id}/{reason}",method=RequestMethod.PUT)
public ResponseEntity<?>blogrejected(@PathVariable int id,@PathVariable String reason,HttpSession session){
	String email=(String)session.getAttribute("LoginId");
	
	if(email==null) {
		Errorclazz error=new Errorclazz(6,"Please login");
		return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
	}
	User user=userdao.getuser(email);
	if(!user.getRole().equals("ADMIN")) {
		Errorclazz error=new Errorclazz(8,"Access Denied");
		return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
	}
	blogdao.blogrejected(id, reason);
	return new ResponseEntity<Void>(HttpStatus.OK);
}
	@RequestMapping(value="/deleteblog/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?>deleteblog(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userdao.getuser(email);
		if(!user.getRole().equals("ADMIN")) {
			Errorclazz error=new Errorclazz(8,"Access Denied");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
             blogdao.deleteblog(id);
             return new ResponseEntity<Void>(HttpStatus.OK);
	
	}
}
