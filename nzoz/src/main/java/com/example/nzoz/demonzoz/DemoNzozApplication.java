package com.example.nzoz.demonzoz;

import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;

public class DemoNzozApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoNzozApplication.class, args);
	}

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
