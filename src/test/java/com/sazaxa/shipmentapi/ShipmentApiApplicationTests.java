package com.sazaxa.shipmentapi;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.notice.Notice;
import com.sazaxa.shipmentapi.order.Order;
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
	void memberRole(){
		Member member = new Member();
	}

	@Test
	void testEnumOutput(){
		Order order = Order.builder()
				.orderNumber("ABC-1234")
				.orderStatus(OrderStatus.PAYMENT_HOLDING)
				.build();
		System.out.println(order.getOrderStatus().status);
	}

	@Test
	void testEnumInput(){
		String name = "결제대기";

		Notice notice = Notice.builder()
				.title("t1")
				.orderStatus(OrderStatus.findByKorean(name))
				.build();

		System.out.println(notice.getOrderStatus().status);
	}

}
