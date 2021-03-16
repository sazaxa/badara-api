package com.sazaxa.shipmentapi;

import com.sazaxa.shipmentapi.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShipmentApiApplicationTests {

	@DisplayName("소숫점 확인 테스트")
	@Test
	void testPrice() {
		double price = 1200;
		int newPrice = (int) (500 * Math.ceil(price / 500));
		System.out.println(newPrice);
	}

	@Test
	void testStatus(){
		System.out.println(OrderStatus.CENTER_INCOME.toString());
		System.out.println(OrderStatus.CENTER_INCOME.status);
	}

}
