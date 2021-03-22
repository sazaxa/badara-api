package com.sazaxa.shipmentapi;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.haporder.HapOrderStatus;
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
		System.out.println(HapOrderStatus.CENTER_INCOME.name());
		System.out.println(HapOrderStatus.CENTER_INCOME.status);
	}

	@Test
	void memberRole(){
		Member member = new Member();
	}

}
