package com.hashkart.assignment.cartitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CartitemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartitemApplication.class, args);
	}

}
