package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.Profilepicturedao;
import com.niit.model.Errorclazz;
import com.niit.model.Profilepic;

@Controller
public class Profilepicturecontroller {
	@Autowired
	Profilepicturedao profilepicdao;
	@RequestMapping(value="/uploadpic",method= RequestMethod.POST)
	public ResponseEntity<?>uploadpic( @RequestBody CommonsMultipartFile image,HttpSession session) {
String email=(String)session.getAttribute("LoginId");
		
		/*String email="a@m.com";*/
		if(email==null) {
			Errorclazz error=new Errorclazz(6,"Please login");
			return new ResponseEntity<Errorclazz>(error,HttpStatus.UNAUTHORIZED);
		}
		Profilepic profilepic=new Profilepic();
		profilepic.setEmail(email);
		profilepic.setImg(image.getBytes());
		profilepicdao.uploadpic(profilepic);
		return new ResponseEntity<Profilepic>(profilepic,HttpStatus.OK);
	}
	@RequestMapping(value="/getimage/{email:.+}",method=RequestMethod.GET)
public @ResponseBody byte[] getpic(@PathVariable String email,HttpSession session) {
	String auth=(String)session.getAttribute("LoginId");
	if(auth==null) {
	
		return null;
	}
	Profilepic pic=profilepicdao.getpic(email);
	if(pic==null)
		return null;
	
	return pic.getImg();
	
}
}
