package com.project.LetsTalk.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.project.LetsTalk.model.Blog;
import com.project.LetsTalk.model.BlogComment;
import com.project.LetsTalk.model.Friend;
import com.project.LetsTalk.model.Job;
import com.project.LetsTalk.model.JobApplication;
import com.project.LetsTalk.model.Message;
import com.project.LetsTalk.model.OutputMessage;
import com.project.LetsTalk.model.ProfilePicture;
import com.project.LetsTalk.model.User;



@Configuration

@ComponentScan("com.project.*")

@EnableTransactionManagement

public class DBConfig {

	private static final String DATABASE_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";

	private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	private static final String DATABASE_USER = "LetsTalk";

	private static final String DATABASE_PASSWORD = "123";

	private static final String HBMDDL = "update";

	private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.Oracle10gDialect";

	@Autowired

	@Bean(name = "dataSource")

	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(DATABASE_DRIVER_CLASS);

		dataSource.setUrl(DATABASE_URL);

		dataSource.setUsername(DATABASE_USER);

		dataSource.setPassword(DATABASE_PASSWORD);

		System.out.println("Oracle connected");

		return dataSource;

	}

	@Autowired

	@Bean(name = "sessionFactory")

	public SessionFactory getSessionFactory() {

		Properties properties = new Properties();

		properties.put("hibernate.hbm2ddl.auto", HBMDDL);

		properties.put("hibernate.dialect", HIBERNATE_DIALECT);

		properties.put("hibernate.show_sql", "true");

		properties.put("hibernate.format_sql", "true");

		LocalSessionFactoryBuilder localSessionFactoryBuider = new LocalSessionFactoryBuilder(getDataSource());

		localSessionFactoryBuider.addProperties(properties);
		localSessionFactoryBuider.addAnnotatedClass(User.class);
		localSessionFactoryBuider.addAnnotatedClass(Job.class);
		localSessionFactoryBuider.addAnnotatedClass(JobApplication.class);
		localSessionFactoryBuider.addAnnotatedClass(Blog.class);
		localSessionFactoryBuider.addAnnotatedClass(BlogComment.class);
		localSessionFactoryBuider.addAnnotatedClass(Friend.class);
		localSessionFactoryBuider.addAnnotatedClass(Message.class);
		localSessionFactoryBuider.addAnnotatedClass(OutputMessage.class);
		localSessionFactoryBuider.addAnnotatedClass(ProfilePicture.class);
		

		System.out.println("=== Tables Created ===");

		SessionFactory sessionFectory = localSessionFactoryBuider.buildSessionFactory();

		return sessionFectory;

	}

	@Autowired
	@Bean("transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
