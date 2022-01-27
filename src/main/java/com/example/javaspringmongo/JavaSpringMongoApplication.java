package com.example.javaspringmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JavaSpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringMongoApplication.class, args);
	}

}
