package com.example;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/reservations")
public class ReservationApiGatewayRestController {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	@Autowired
	@Output(Source.OUTPUT)
	private MessageChannel messageChannel;
	
	@RequestMapping(method = RequestMethod.POST)
	public void write(@RequestBody Reservation r) {
		this.messageChannel.send(MessageBuilder.withPayload(r.getReservationName()).build());
	}
	
	ParameterizedTypeReference<Resources<Reservation>> ptr = new ParameterizedTypeReference<Resources<Reservation>>() {
	};
	
	public Collection<String> getReservationNamesFallback() {
		return Collections.emptyList();
	}
	
	@HystrixCommand(fallbackMethod = "getReservationNamesFallback")
	@RequestMapping("/names")
	public Collection<String> getReservationNames() {
		 ResponseEntity<Resources<Reservation>> responseEntity = this.restTemplate.exchange("http://reservation-service/reservations", HttpMethod.GET, null, ptr);
		return responseEntity
				.getBody()
				.getContent()
				.stream()
				.map(Reservation::getReservationName)
				.collect(Collectors.toList());
	}
}
