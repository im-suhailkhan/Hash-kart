package com.hashkart.assignment.purchasedItem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PurchasedItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchasedItemApplication.class, args);
	}

}
