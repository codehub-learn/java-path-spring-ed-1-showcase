package gr.codelearn.spring.showcase.app.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class OrderItem extends BaseModel {
	private Product product;
	private Integer quantity;
	private BigDecimal price;
}
