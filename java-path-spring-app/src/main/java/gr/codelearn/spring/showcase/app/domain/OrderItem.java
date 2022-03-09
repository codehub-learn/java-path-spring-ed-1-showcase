package gr.codelearn.spring.showcase.app.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItem {
	private Product product;
	private Integer quantity;
	private BigDecimal price;
}
