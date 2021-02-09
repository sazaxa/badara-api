package com.sazaxa.shipmentapi;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class ShipmentApiApplicationTests {

	@Test
	void contextLoads() {
		double price = 1200;

		int newPrice = (int) (500 * Math.ceil(price / 500));

		System.out.println(newPrice);
	}

}
