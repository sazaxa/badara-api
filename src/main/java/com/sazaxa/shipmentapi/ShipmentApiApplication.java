package com.sazaxa.shipmentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
public class ShipmentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentApiApplication.class, args);
	}

}
