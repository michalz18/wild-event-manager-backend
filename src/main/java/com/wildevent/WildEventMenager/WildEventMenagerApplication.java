package com.wildevent.WildEventMenager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WildEventMenagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WildEventMenagerApplication.class, args);
	}

}

