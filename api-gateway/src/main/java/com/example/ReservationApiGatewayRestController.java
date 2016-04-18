package com.example;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
//@RequestMapping("/")
public class ReservationApiGatewayRestController {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

//	ParameterizedTypeReference<Resources<Reservation>> ptr = new ParameterizedTypeReference<Resources<Reservation>>() {
//	};
	
	@HystrixCommand(fallbackMethod = "getRandomStringFallback")
	@RequestMapping("/random")
	public String getRandomString() {
		 ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://random-words-service/word", HttpMethod.GET, null, String.class);
		return responseEntity.getBody();
	}
	
	public String getRandomStringFallback() {
		return UUID.randomUUID().toString();
	}
}
