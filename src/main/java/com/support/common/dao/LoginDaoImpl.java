package com.support.common.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.support.common.beans.FilePollerDetail;
import com.support.common.beans.User;
import com.support.common.config.DatabaseConfig;
import com.support.common.constants.Queries;




public class LoginDaoImpl implements LoginDao {
	
	private static JdbcTemplate jdbcTemplate;
	private static DataSource dataSource;
	private static final Logger logger = Logger.getLogger(FilePollerDaoImpl.class);
	public LoginDaoImpl(){
		 ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
		 dataSource = (DataSource) context.getBean("dataSource");
		    
		 if (null == jdbcTemplate) {
			 jdbcTemplate = new JdbcTemplate(dataSource);
			}
	}


	@Override
	public boolean isValidUser(User user) {
		logger.info("Authenticating User.");

		String sql = Queries.AUTHENTICATE_USER;
//		Integer userCnt = jdbcTemplate.queryForObject(sql,
//				new Object[] { user.getName(),user.getPassword() }, Integer.class);
//		if(userCnt > 0){
//			return true;
//		}
//		return false;
		return true;
	}



}
