package com.example.Erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroServiceProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceProjetApplication.class, args);
	}

}
