package com.support.common.dao;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.support.common.beans.SmartdocsUser;
import com.support.common.beans.User;
import com.support.common.config.DatabaseConfig;
import com.support.common.constants.Queries;


public class SmartdocsUserDaoImpl implements SmartdocsUserDao {
	
	private static JdbcTemplate jdbcTemplate;
	private static DataSource dataSource;
	
	public SmartdocsUserDaoImpl(){
		 ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
		 dataSource = (DataSource) context.getBean("dataSource");
		    
		 if (null == jdbcTemplate) {
			 jdbcTemplate = new JdbcTemplate(dataSource);
			}
	}	

	@Override
	public boolean createUser(SmartdocsUser smartdocsUser) {
		// TODO Auto-generated method stub
		String createUserSql = Queries.CREATE_USER;
		Object[] obj = new Object[] { smartdocsUser.getUserId().toUpperCase(), smartdocsUser.getRoles(), smartdocsUser.getFirstName(),
				smartdocsUser.getLastName(), smartdocsUser.getAccType(), smartdocsUser.getClientId(),
				smartdocsUser.getClientName(), smartdocsUser.getRegion(), smartdocsUser.getEnabled(),
				smartdocsUser.getWrhsCd(), Integer.parseInt(smartdocsUser.getVltId()) };

		int updVal = jdbcTemplate.update(createUserSql, obj);
		System.out.println("Status of insertion of Smartdocs User :" + updVal);
		if(updVal == 1)
			return true;
		
		return false;
	}

	@Override
	public boolean updateUser(SmartdocsUser smartdocsUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserRoles(User user) {
		String getUserRoleSql = Queries.GET_USER_ROLES;
		String userRole = jdbcTemplate.queryForObject(getUserRoleSql, new Object[] { user.getName().toUpperCase() }, String.class);
		return userRole;
	}

	@Override
	public String getUserStatus(User user) {
		String getUserStatusSql = Queries.GET_USER_STATUS;
		String userStatus = jdbcTemplate.queryForObject(getUserStatusSql, new Object[] { user.getName().toUpperCase() }, String.class);
		return userStatus;
	}

	@Override
	public String getUserVault(User user) {
		String getUserVaultSql = Queries.GET_USER_VLT;
		String userVault = jdbcTemplate.queryForObject(getUserVaultSql, new Object[] { user.getName().toUpperCase() }, String.class);
		return userVault;
	}

	@Override
	public boolean updateUserRole(SmartdocsUser smartdocsUser) {
		String updateUserSql = Queries.UPDATE_USER_ROLE;
		Object[] obj = new Object[] { smartdocsUser.getRoles(), smartdocsUser.getUserId().toUpperCase()};

		int updVal = jdbcTemplate.update(updateUserSql, obj);
		System.out.println("Status of Smartdocs User Role update:" + updVal);
		if(updVal == 1)
			return true;
		
		return false;

	}

	@Override
	public boolean updateUserED(SmartdocsUser smartdocsUser) {
		String updateUserEDSql = Queries.UPDATE_USER_ED;
		Object[] obj = new Object[] { smartdocsUser.getEnabled(), smartdocsUser.getUserId().toUpperCase()};

		int updVal = jdbcTemplate.update(updateUserEDSql, obj);
		System.out.println("Status of Smartdocs User ED update:" + updVal);
		if(updVal == 1)
			return true;
		
		return false;
	}

	@Override
	public boolean updateUserVault(SmartdocsUser smartdocsUser) {
		String updateUserVaultSql = Queries.UPDATE_USER_VAULT;
		Object[] obj = new Object[] { smartdocsUser.getVltId(), smartdocsUser.getUserId().toUpperCase()};

		int updVal = jdbcTemplate.update(updateUserVaultSql, obj);
		System.out.println("Status of Smartdocs User vault update:" + updVal);
		if(updVal == 1)
			return true;
		
		return false;
	}
}
