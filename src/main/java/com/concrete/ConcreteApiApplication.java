package com.concrete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.concrete.model.repository.impl.MyRepositoryImpl;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.concrete", repositoryBaseClass = MyRepositoryImpl.class)
@Import(MailConfiguration.class)
public class ConcreteApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ConcreteApiApplication.class, args);
	}

	//	@Bean
	//	public ViewResolver internalResourceViewResolver() {
	//		final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	//		resolver.setPrefix("/WEB-INF/views/");
	//		resolver.setSuffix(".jsp");
	//		resolver.setViewClass(JstlView.class);
	//		resolver.setOrder(1);
	//		return resolver;
	//	}
}
