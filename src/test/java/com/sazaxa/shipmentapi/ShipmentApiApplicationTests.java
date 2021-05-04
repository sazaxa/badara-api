package com.sazaxa.shipmentapi;

import com.sazaxa.shipmentapi.faq.Faq;
import com.sazaxa.shipmentapi.faq.FaqService;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.notice.Notice;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ShipmentApiApplicationTests {

	@Autowired
	private FaqService faqService;

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

	@Test
	void testLocalDateTime(){
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);
	}

	@Test
	void testFaq(){
		Faq faq = faqService.saveFaq(FaqSaveRequestDto.builder()
				.title("test-title-1")
				.content("test-contest-2")
				.build());

		System.out.println(faq.getCreatedDate());

	}

}
