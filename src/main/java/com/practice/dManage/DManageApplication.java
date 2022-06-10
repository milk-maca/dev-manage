package com.practice.dManage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing			//Entity/Developer 에서 사용하기 위해 Auditing 기능 활성화
@SpringBootApplication
public class DManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DManageApplication.class, args);
	}

}
