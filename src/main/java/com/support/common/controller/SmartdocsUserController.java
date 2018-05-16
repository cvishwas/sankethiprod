package com.support.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.support.common.beans.SmartdocsUser;
import com.support.common.beans.User;
import com.support.common.dao.SmartdocsUserDaoImpl;

@Controller
@RequestMapping("/user")
public class SmartdocsUserController {

	
	@PostMapping(value="/CreateUser")
	public @ResponseBody String createUser(@RequestBody SmartdocsUser user, ModelMap model) {
		SmartdocsUserDaoImpl smartdocsUserDaoImpl = new SmartdocsUserDaoImpl();
		if(smartdocsUserDaoImpl.createUser(user))
			return "UserCreated";
		
		return "UserCreationFailure";


	}
	
	@PostMapping(value="/UpdateUserRole")
	public @ResponseBody String updateUserRole(@RequestBody SmartdocsUser user, ModelMap model) {
		SmartdocsUserDaoImpl smartdocsUserDaoImpl = new SmartdocsUserDaoImpl();
		if(smartdocsUserDaoImpl.updateUserRole(user))
			return "UserRoleUpdated";
		
		return "UserUpdateFailure";


	}	
	
	@PostMapping(value="/UpdateUserED")
	public @ResponseBody String updateUserED(@RequestBody SmartdocsUser user, ModelMap model) {
		SmartdocsUserDaoImpl smartdocsUserDaoImpl = new SmartdocsUserDaoImpl();
		if(smartdocsUserDaoImpl.updateUserED(user))
			return "UserRoleUpdated";
		
		return "UserUpdateFailure";


	}
	
	
	@PostMapping(value="/UpdateUserVault")
	public @ResponseBody String updateUserVault(@RequestBody SmartdocsUser user, ModelMap model) {
		SmartdocsUserDaoImpl smartdocsUserDaoImpl = new SmartdocsUserDaoImpl();
		if(smartdocsUserDaoImpl.updateUserVault(user))
			return "UserVaultUpdated";
		
		return "UserUpdateFailure";


	}	
	
	@PostMapping(value="/GetUserRoles")
	public @ResponseBody String getUserRoles(@RequestBody User user, ModelMap model) {
		SmartdocsUserDaoImpl smartdocsUserDaoImpl = new SmartdocsUserDaoImpl();
		return smartdocsUserDaoImpl.getUserRoles(user);
	}	

	@PostMapping(value="/GetUserStatus")
	public @ResponseBody String getUserStatus(@RequestBody User user, ModelMap model) {
		SmartdocsUserDaoImpl smartdocsUserDaoImpl = new SmartdocsUserDaoImpl();
		return smartdocsUserDaoImpl.getUserStatus(user);
	}	
	
	@PostMapping(value="/GetUserVault")
	public @ResponseBody String getUserVault(@RequestBody User user, ModelMap model) {
		SmartdocsUserDaoImpl smartdocsUserDaoImpl = new SmartdocsUserDaoImpl();
		return smartdocsUserDaoImpl.getUserVault(user);
	}	
	
}