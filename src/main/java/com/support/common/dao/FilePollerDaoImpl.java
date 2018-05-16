package com.support.common.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.support.common.beans.FilePollerDetail;
import com.support.common.config.DatabaseConfig;
import com.support.common.constants.Queries;




public class FilePollerDaoImpl implements FilePollerDao {
	
	private static JdbcTemplate jdbcTemplate;
	private static DataSource dataSource;
	private static final Logger logger = Logger.getLogger(FilePollerDaoImpl.class);
	public FilePollerDaoImpl(){
		 ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
		 dataSource = (DataSource) context.getBean("dataSource");
		    
		 if (null == jdbcTemplate) {
			 jdbcTemplate = new JdbcTemplate(dataSource);
			}
	}

	@Override
	public List<FilePollerDetail> getFilePollerDetail(String sourceSystem) {
		logger.info("Calling database to get status of doc barcode whether FP was successful or not.");
		List<FilePollerDetail> filePollerDetailList = new ArrayList<FilePollerDetail>();

		logger.info("Retrieving the file poller details :");
		String sql = Queries.QUERY_TO_REPORT_FILE_POLLER;
		if (!sourceSystem.equals("ALL")) {
			sql = sql + " and SRC_SYSTM = '" + sourceSystem + "'";
		}
		logger.info("Query to retrieve FP detail:" + sql);
		filePollerDetailList = jdbcTemplate.query(sql, new FilePollerDetail());

		return filePollerDetailList;
	}



}
