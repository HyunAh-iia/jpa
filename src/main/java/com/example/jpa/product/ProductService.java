package com.example.jpa.product;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	private final ProductRepository repository;

	@Transactional(readOnly = true)
	public List<ProductDto.Response> streamAll() {
		return repository.streamAll()
			.map(entity -> new ProductDto.Response(entity))
			.collect(Collectors.toList());
	}

	@Transactional
	public void save(Collection<ProductDto.Create> products) {
		Set<Product> newProducts = products.stream()
			.map(dto -> Product.builder().name(dto.getName()).build())
			.collect(Collectors.toSet());

		repository.saveAll(newProducts);
	}
}
