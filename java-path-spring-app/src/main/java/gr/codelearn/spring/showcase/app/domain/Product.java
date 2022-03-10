package gr.codelearn.spring.showcase.app.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class Product extends BaseModel {
	private String serial;
	private String name;
	private BigDecimal price;
	private Category category;
}
