package com.netec.neobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class NeobankApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeobankApplication.class, args);
	}

}
