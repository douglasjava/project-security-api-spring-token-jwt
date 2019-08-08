package com.montreal.mock.montrealapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.montreal.mock.montrealapi")
public class MontrealApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MontrealApiApplication.class, args);
	}

}
