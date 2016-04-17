package com.example;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

//@EnableBinding(Sink.class)
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReservationRepository rr) {
		return args -> {
			Arrays.asList("Pesho, Tosho, GOsho, Tsetso, Miro, Ganka".split(", ")).forEach(name -> {
				rr.save(new Reservation(name));
			});
			rr.findAll().forEach(System.out::println);
		};
	}
}

//@MessageEndpoint
//class MessageReservationReceiver {
//	
//	@Autowired
//	ReservationRepository repo;
//	
//	@ServiceActivator (inputChannel = Sink.INPUT)
//	public void acceptReservation(String rn) {
//		repo.save(new Reservation(rn));
//	}
//}