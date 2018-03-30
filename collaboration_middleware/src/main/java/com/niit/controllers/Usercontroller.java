package com.niit.controllers;

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

import com.niit.dao.Userdao;
import com.niit.model.Data;
import com.niit.model.Errorclazz;
import com.niit.model.User;

@RestController

public class Usercontroller {
	@Autowired
	private Userdao userdao;
	@RequestMapping(value="/userRegistration",method=RequestMethod.POST)
	public ResponseEntity<?> userRegistration(@RequestBody User user){
		System.out.println(user.getFirstName());
		if(!userdao.isemailvalid(user.getEmail())) {
			Errorclazz error=new Errorclazz(2,"Email is already prsent try with new one" );
			return new ResponseEntity<Errorclazz>(error,HttpStatus.CONFLICT);
		}
		if(!userdao.isphonenumbervalid(user.getPhoneNumber())) {
			Errorclazz error=new Errorclazz(4,"Phonenumber is already prsent try with new one" );
			return new ResponseEntity<Errorclazz>(error,HttpStatus.CONFLICT);
		}
		try {
			
			userdao.UserRegistration(user);
			
		}
		catch(Exception e) {
			Errorclazz error=new Errorclazz(1,"unable to insert");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	@RequestMapping(value="/EmailLogin",method=RequestMethod.POST)
	public ResponseEntity<?>EmailLogin(@RequestBody Data data, HttpSession session){
		
		User validuser=userdao.Emaillogin(data);
		System.out.println(data.getInp()+""+data.getInp2());
		if(validuser==null) {
			Errorclazz error=new Errorclazz(3,"Email or Password was Incorrect");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
			
		}else {
			
			session.setAttribute("LoginId",data.getInp());
			validuser.setOnline(true);
			userdao.update(validuser);
			
		return new ResponseEntity<User>(validuser,HttpStatus.ACCEPTED);
		}
	}
	@RequestMapping(value="/PhonenumberLogin",method=RequestMethod.POST)
	public ResponseEntity<?>PhonenumberLogin(@RequestBody Data data,HttpSession session){
		User validuser=userdao.PhoneNumberLogin(data);
		if(validuser==null) {
			Errorclazz error=new Errorclazz(3,"Phone number or Password was Incorrect");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
			
		}else {
			
			session.setAttribute("LoginId",validuser.getEmail());
			System.out.println(session.getAttribute("LoginId"));
			validuser.setOnline(true);
			userdao.update(validuser);
		return new ResponseEntity<User>(validuser,HttpStatus.ACCEPTED);
		}
		
	}
	@RequestMapping(value="/EmailLogout",method=RequestMethod.PUT)
	public ResponseEntity<?>emaillogout(HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		if(email==null) {
			Errorclazz error=new Errorclazz(5,"Your are unauthorized user.Please Login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}else {
		    User user=userdao.getuser(email);
			user.setOnline(false);
			userdao.update(user);
			session.removeAttribute("LoginId");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	
	}
	@RequestMapping(value="/PhonenumberLogout",method=RequestMethod.PUT)
	public ResponseEntity<?>phonenumberlogout(HttpSession session){
		String phoneNumber=(String)session.getAttribute("LoginId");
		if(phoneNumber==null) {
			Errorclazz error=new Errorclazz(5,"Your are unauthorized user.Please Login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}else {
		    User user=userdao.getuser2(phoneNumber);
			user.setOnline(false);
			userdao.update(user);
			session.removeAttribute("LoginId");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	
	}
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public ResponseEntity<?>getUser(HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		if(email==null) {
			Errorclazz error=new Errorclazz(9,"Your are unauthorized user.Please Login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
			
		}
		User user=userdao.getuser(email);
		 return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ResponseEntity<?>edit(@RequestBody User user,HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		if(email==null) {
			Errorclazz error=new Errorclazz(10,"Your are unauthorized user.Please Login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);	
		}
		
		userdao.update(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/gettAllAdmin",method=RequestMethod.GET)
	public ResponseEntity<?>getalladmin(HttpSession session){
		String email=(String)session.getAttribute("LoginId");
		if(email==null) {
			Errorclazz error=new Errorclazz(10,"Your are unauthorized user.Please Login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);	
		}
		List <User> admin=userdao.getAllAdmin();
		return new ResponseEntity<List<User>>(admin,HttpStatus.OK);
	}
	@RequestMapping(value="/deleteAdmin/{email:.+}",method=RequestMethod.DELETE)
	public ResponseEntity<?>deleteAdmin(@PathVariable String email,HttpSession session){
		/*String email="admin@m.com";
		System.out.println(email);*/
		String email1=(String)session.getAttribute("LoginId");
		if(email1==null) {
			Errorclazz error=new Errorclazz(10,"Your are unauthorized user.Please Login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);	
		}
		System.out.println("Test"+email);
		userdao.deleteAdmin(email);
		/*List<User>admin=userdao.getAllAdmin();*/
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
