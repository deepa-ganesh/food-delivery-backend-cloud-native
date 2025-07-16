package io.github.deepaganesh.fooddelivery.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@EnableCaching // Required for Spring's Cache Abstraction
@EnableFeignClients(basePackages = "io.github.deepaganesh.fooddelivery.order.client")
@SpringBootApplication
@ComponentScan(basePackages = "io.github.deepaganesh.fooddelivery")
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
