package com.example.jpa.product;

import lombok.Builder;
import lombok.Getter;

public class ProductDto {
	@Getter
	public static class Response {
		private Long id;
		private String name;

		public Response(Product entity) {
			id = entity.getId();
			name = entity.getName();
		}
	}

	@Getter
	public static class Create {
		private String name;

		@Builder
		public Create(String name) {
			this.name = name;
		}
	}
}
