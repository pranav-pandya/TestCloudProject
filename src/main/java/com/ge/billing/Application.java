package com.ge.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



//@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.ge.billing.repository")
@EntityScan(basePackages = {"com.ge.billing.model"})
@ComponentScan(basePackages = {"com.ge"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
