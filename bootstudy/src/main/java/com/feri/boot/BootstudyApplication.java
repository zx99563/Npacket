package com.feri.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BootstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootstudyApplication.class, args);
	}

}
