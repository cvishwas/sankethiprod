package com.support.common.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.support.common.beans.FilePollerDetail;
import com.support.common.beans.StatusAndCount;
import com.support.common.config.DatabaseConfig;
import com.support.common.constants.Queries;


public class SmartdocsImagingDaoImpl implements SmartdocsImagingDao {
	
	private static JdbcTemplate jdbcTemplate;
	private static DataSource dataSource;
	
	public SmartdocsImagingDaoImpl(){
		 ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
		 dataSource = (DataSource) context.getBean("dataSource");
		    
		 if (null == jdbcTemplate) {
			 jdbcTemplate = new JdbcTemplate(dataSource);
			}
	}	



	@Override
	public List<StatusAndCount> getImagingStatusForShipment(FilePollerDetail fpd) {
		List<StatusAndCount> statusAndCountList = null;
		String getStatusForShipmentSql = Queries.GET_STATUS_FOR_SHIPMENT_SQL;
		
		statusAndCountList = jdbcTemplate.query(getStatusForShipmentSql, new Object[] { fpd.getShipmentNumber() }, new StatusAndCount());		
		return statusAndCountList;
	}
	
	@Override
	public List<FilePollerDetail> getImagingDetailsForStatus(FilePollerDetail fpd) {
		List<FilePollerDetail> fpDetailList = new ArrayList<FilePollerDetail>();
		String getStatusForShipmentSql = Queries.QUERY_TO_REPORT_FILE_POLLER_ON_STATUS_AND_SHIPMENT;
		
		fpDetailList = jdbcTemplate.query(getStatusForShipmentSql, new Object[] { fpd.getStatus(), fpd.getShipmentNumber() }, new FilePollerDetail());		
		return fpDetailList;
	}



	@Override
	public List<FilePollerDetail> getImagingStatusForDocument(FilePollerDetail fpd) {
		List<FilePollerDetail> fpDetailList = new ArrayList<FilePollerDetail>();
		String getStatusForDocumentSql = Queries.GET_STATUS_FOR_DOCUMENT_SQL;
		
		fpDetailList = jdbcTemplate.query(getStatusForDocumentSql, new Object[] { fpd.getDocumentBarcode() }, new FilePollerDetail());			
		return fpDetailList;
	}



	@Override
	public List<FilePollerDetail> getImagingDetailsForDuration(String fromDt, String toDt) {
		List<FilePollerDetail> fpDetailList = new ArrayList<FilePollerDetail>();
		String getStatusForDurationSql = Queries.QUERY_TO_REPORT_FILE_POLLER_ON_DURATION;
		
		fpDetailList = jdbcTemplate.query(getStatusForDurationSql, new Object[] { fromDt, toDt }, new FilePollerDetail());		
		return fpDetailList;
	}	
	
}
