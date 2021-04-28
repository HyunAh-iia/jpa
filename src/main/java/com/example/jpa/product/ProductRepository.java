package com.example.jpa.product;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select p from Product p")
	Stream<Product> streamAll();
}
