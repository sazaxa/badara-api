package com.sazaxa.shipmentapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShipmentApiApplicationTests {

	@DisplayName("소숫점 확인 테스트")
	@Test
	void testPrice() {
		Double price = 1200D;
		Integer newPrice = (int) (500 * Math.ceil(price / 500));
		assertThat(newPrice).isEqualTo(1500);
	}
}
