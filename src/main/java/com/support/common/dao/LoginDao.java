package com.support.common.dao;

import com.support.common.beans.User;


public interface LoginDao {
	public boolean isValidUser(User user);
}
