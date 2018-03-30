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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.Jobdao;
import com.niit.dao.Userdao;
import com.niit.model.Errorclazz;
import com.niit.model.Job;
import com.niit.model.User;

@RestController
public class Jobcontroller {
	@Autowired
	private Userdao userdao;
	@Autowired
	private Jobdao jobdao;
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?>getalljobs(HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobdao.getalljobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addjob",method=RequestMethod.POST)
	public ResponseEntity<?>addJob(@RequestBody Job job,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userdao.getuser(email);
		System.out.println(user.getRole());
		if(!(user.getRole().equals("ADMIN"))) {
			Errorclazz error=new Errorclazz(7,"Unauthorized access");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		try {
			job.setPostedon(new Date());
			
			jobdao.addJob(job);
			
		}catch(Exception e){
			Errorclazz error=new Errorclazz(8,"unable to insert job details");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK) ;
		
	}
	@RequestMapping(value="/getjob/{id}",method=RequestMethod.GET)
	public ResponseEntity<?>getjob( @PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		try
		{
			Job job=jobdao.getjob(id);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
			}
		catch(Exception e) {
			Errorclazz error=new Errorclazz(8,"unable to get job details");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	
		
	}

}
