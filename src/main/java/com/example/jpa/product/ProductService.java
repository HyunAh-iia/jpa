package com.example.jpa.product;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	@PersistenceContext
	private EntityManager entityManager;
	private final ProductRepository repository;

	@Transactional(readOnly = true)
	public void orderAll() {
		Stream<Product> products = repository.streamAll();
		products.forEach( product -> {
			// 제품에 대한 생산 요청 API를 호출한다.
			// ...

			// 메모리에 올라간 Entity를 GC이 클리어할 수 있도록 풀어둠
			entityManager.detach(product); //JPA 2.0, to detach a single entity from persistence context
		});
	}
}
