package com.target.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyRetailDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRetailDemoApplication.class, args);
	}

}
