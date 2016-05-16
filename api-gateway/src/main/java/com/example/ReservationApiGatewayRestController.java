package com.example;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/")
public class ReservationApiGatewayRestController {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	// ParameterizedTypeReference<Resources<Reservation>> ptr = new
	// ParameterizedTypeReference<Resources<Reservation>>() {
	// };

	@CrossOrigin
	@HystrixCommand(fallbackMethod = "getRandomStringFallback")
	@RequestMapping("/random")
	public String getRandomString() {
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://random-words-service/word",
				HttpMethod.GET, null, String.class);
		return responseEntity.getBody();
	}

	public String getRandomStringFallback() {
		return UUID.randomUUID().toString();
	}

	@CrossOrigin
	@HystrixCommand(fallbackMethod = "concatenateFallback")
	@RequestMapping("/concatenate/{inputString}")
	public String concatenate(@PathVariable String inputString, @RequestParam String otherString) {
		String url = "http://string-util-service/concat/" + inputString + "?otherString=" + otherString;
		ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		return responseEntity.getBody();
	}

	public String concatenateFallback(String inputString, String otherString) {
		return "concatanation is unavailable right now";
	}

	@CrossOrigin
	@HystrixCommand(fallbackMethod = "substringFallback")
	@RequestMapping("/substring/{inputString}")
	public String substring(@PathVariable String inputString, @RequestParam int start, @RequestParam int end) {
		String url = "http://string-util-service/substring/" + inputString + "?start=" + start + "&end=" + end;
		ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		return responseEntity.getBody();
	}

	public String substringFallback(String inputString, int start, int end) {
		return "substring is unavailable right now";
	}

}
