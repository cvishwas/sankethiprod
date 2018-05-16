package com.support.common.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.support.common" }, 
excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Bean
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/smartDocDatasource");
        return dataSource;
    } 	

//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
//	    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//	    emf.setPersistenceUnitName("persistenceUnit");
//	    emf.setDataSource(dataSource());
//	    return emf;
//	}


	
	

}
