package com.example.jpa.product;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService service;

	@Test
	@DisplayName("bulk save + StreamAll 성공 테스트")
	void testSaveAllAndStreamAll() {
		List<ProductDto.Create> request = new ArrayList<>();

		for (int i = 0; i < 500000; i++) {
			request.add(ProductDto.Create.builder().name(String.valueOf(i)).build());
		}

		service.save(request);

		List<ProductDto.Response> response = service.streamAll();
		Assertions.assertThat(response.size()).isEqualTo(500000);
	}
}
