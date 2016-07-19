package com.concrete;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.concrete.model.repository.impl.MyRepositoryImpl;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.concrete", repositoryBaseClass = MyRepositoryImpl.class)
@ComponentScan(basePackages = "com.concrete")
public class PersistenceConfigTest {

	@Bean
	public DataSource dataSource() {
		final String user = "sa";
		final String password = "";
		final String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
		final DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, password);
		dataSource.setDriverClassName("org.h2.Driver");

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.H2);
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.concrete");
		factory.setPersistenceUnitName("concretePU");
		factory.setDataSource(dataSource());
		factory.setJpaProperties(new Properties());

		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		txManager.setDataSource(dataSource());
		return txManager;
	}

}
