package pl.xsolve.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookingAmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingAmqpApplication.class, args);
	}
}
