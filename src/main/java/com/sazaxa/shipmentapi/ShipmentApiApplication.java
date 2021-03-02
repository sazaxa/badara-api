package com.sazaxa.shipmentapi;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShipmentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentApiApplication.class, args);
	}

	/**
	 * dozerMapper를 DI를 통하여 사용하기 위한 메소드 입니다.
	 * @return
	 */
	@Bean
	public Mapper dozerMapper() {
		return DozerBeanMapperBuilder.buildDefault();
	}

}
