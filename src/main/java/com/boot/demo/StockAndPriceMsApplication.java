package com.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StockAndPriceMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAndPriceMsApplication.class, args);
	}

}
