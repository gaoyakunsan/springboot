package com.springboot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan("com.springboot.core.domain")
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.springboot.core.business","com.springboot.admin"})
@EnableJpaRepositories("com.springboot.core.business.dao")


/*@SpringBootApplication(scanBasePackages = {"com.springboot.core.business"})
@EntityScan("com.springboot.core.domain")
@EnableJpaRepositories("com.springboot.core.business.dao")*/
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
