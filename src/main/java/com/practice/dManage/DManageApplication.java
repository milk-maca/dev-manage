package com.practice.dManage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DManageApplication.class, args);
	}

}
