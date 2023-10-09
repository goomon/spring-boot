package com.github.goomon.boot;

import com.github.goomon.boot.annotation.MySpringBootApplication;
import org.springframework.boot.SpringApplication;

@MySpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
