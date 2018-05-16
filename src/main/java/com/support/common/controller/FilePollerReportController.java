package com.support.common.controller;


import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.support.common.config.DatabaseConfig;
import com.support.common.report.FilePollerReport;

@Controller
@RequestMapping("/fp")
public class FilePollerReportController {
	private static final Logger logger = Logger.getLogger(FilePollerReportController.class);
	private static DataSource dataSource;
	
	 @RequestMapping(value = "/{name}", method = RequestMethod.GET)
	 public void generateFilePollerEODReport(@PathVariable String name, ModelMap model) {
		 logger.info("Generating EOD FilePoller Report"+dataSource);
		 ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
		 dataSource = (DataSource) context.getBean("dataSource");
		 
		 FilePollerReport.generateEODReport(dataSource);
		 
//		 return "list";
	 }	

}
