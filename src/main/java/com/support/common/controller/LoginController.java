package com.support.common.controller;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.support.common.beans.User;
import com.support.common.config.DatabaseConfig;
import com.support.common.dao.LoginDaoImpl;

@Controller
@RequestMapping("/login")
public class LoginController {


	@RequestMapping(value="/cred", method = RequestMethod.GET)
	public String getLoginPage( ModelMap model) {
		return "login";

	}
	
	@PostMapping(value="/cred/auth")
	public @ResponseBody String authenticateUser(@RequestBody User user, ModelMap model) {
		LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
		if(loginDaoImpl.isValidUser(user)){
			return "login";
		} 
			return "loginfailure";


	}	
	
	@RequestMapping(value="/cred/suppmenu", method = RequestMethod.GET)
	public String getSuppMenuPage( ModelMap model) {
		return "SupportLandingPage";

	}
	
}