package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RandomWordsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomWordsServiceApplication.class, args);
	}
	
	
}