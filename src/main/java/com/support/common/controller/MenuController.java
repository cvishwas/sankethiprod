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
@RequestMapping("/nav")
public class MenuController {


	@RequestMapping(value="/User/CreateUser", method = RequestMethod.GET)
	public String createUser( ModelMap model) {
		return "CreateUserLandingPage";

	}
	
	@RequestMapping(value="/ModifyUser/roles", method = RequestMethod.GET)
	public String modifyUserRole(ModelMap model) {
		model.addAttribute("modify", "roles");
		return "ModifyUserLandingPage";
	}	
	
	@RequestMapping(value="/ModifyUser/ed", method = RequestMethod.GET)
	public String modifyUserED(ModelMap model) {
		model.addAttribute("modify", "enabledisable");
		return "ModifyUserLandingPage";
	}	
	
	@RequestMapping(value="/ModifyUser/vault", method = RequestMethod.GET)
	public String modifyUserVault(ModelMap model) {
		model.addAttribute("modify", "vault");
		return "ModifyUserLandingPage";
	}
	
	@RequestMapping(value="/imaging/shipment", method = RequestMethod.GET)
	public String getImagingStByShipment(ModelMap model) {
		model.addAttribute("imaging", "shipment");
		return "ImagingStatusLandingPage";
	}
	
	@RequestMapping(value="/imaging/duration", method = RequestMethod.GET)
	public String getImagingStByDuration(ModelMap model) {
		model.addAttribute("imaging", "duration");
		return "ImagingStatusLandingPage";
	}
	
	@RequestMapping(value="/imaging/document", method = RequestMethod.GET)
	public String getImagingStByDocument(ModelMap model) {
		model.addAttribute("imaging", "document");
		return "ImagingStatusLandingPage";
	}	
	
}