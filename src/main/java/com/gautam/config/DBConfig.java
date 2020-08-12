package com.gautam.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import com.gautam.model.Department;
//import com.gautam.model.Employee;
//import com.gautam.model.Location;


@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DBConfig {

	@Autowired
	private Environment environment;
	
	@Bean
	DataSource getDataSource() {
		BasicDataSource basicDataSource=new BasicDataSource();
		basicDataSource.setDriverClassName(environment.getProperty("db.classname"));;
		basicDataSource.setUrl(environment.getProperty("db.url"));
		basicDataSource.setUsername(environment.getProperty("db.username"));
		basicDataSource.setPassword(environment.getProperty("db.password"));
		return basicDataSource;
	}
	
	@Bean("sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		
		//hibernate detail
		props.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
		props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		
		//connection pooling details
		props.put(org.hibernate.cfg.Environment.C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
		props.put(org.hibernate.cfg.Environment.C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
		props.put(org.hibernate.cfg.Environment.C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(org.hibernate.cfg.Environment.C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
		props.put(org.hibernate.cfg.Environment.C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.statement"));
		
		factoryBean.setHibernateProperties(props);
		factoryBean.setDataSource(getDataSource());
		factoryBean.setPackagesToScan("com.gautam.model");
		
		
//		factoryBean.setAnnotatedClasses(Employee.class, Department.class, Location.class);
		
		return factoryBean;
		
	} 
	
	@Bean
	public HibernateTransactionManager getTransactionManager(){
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
	
	
}
