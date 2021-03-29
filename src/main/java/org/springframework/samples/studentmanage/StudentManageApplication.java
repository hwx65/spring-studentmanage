package org.springframework.samples.studentmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(proxyBeanMethods = false)
@EnableCaching
public class StudentManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManageApplication.class, args);
	}

}
