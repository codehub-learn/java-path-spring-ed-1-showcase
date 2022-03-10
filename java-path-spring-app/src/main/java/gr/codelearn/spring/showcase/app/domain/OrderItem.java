package gr.codelearn.spring.showcase.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseModel {
	private Product product;
	private Integer quantity;
	private BigDecimal price;
}
