package com.support.common.dao;

import com.support.common.beans.SmartdocsUser;
import com.support.common.beans.User;

public interface SmartdocsUserDao {
	public boolean createUser(SmartdocsUser smartdocsUser);
	public boolean updateUser(SmartdocsUser smartdocsUser);
	public boolean updateUserRole(SmartdocsUser smartdocsUser);
	public boolean updateUserED(SmartdocsUser smartdocsUser);
	public boolean updateUserVault(SmartdocsUser smartdocsUser);
	public String getUserRoles(User user);
	public String getUserStatus(User user);
	public String getUserVault(User user);

}
