package software.xsolve.springcloud.scanmed;


import java.time.Clock;

import org.springframework.boot.SpringApplication;

import com.gargoylesoftware.htmlunit.WebClient;

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public Clock clock() {
		return Clock.systemDefaultZone();
	}

	public WebClient webClient() {
		return new WebClient();
	}
}