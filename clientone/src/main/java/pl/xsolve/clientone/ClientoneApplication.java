package pl.xsolve.clientone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ClientoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientoneApplication.class, args);
	}
}
