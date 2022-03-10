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
public class Product extends BaseModel {
	private String serial;
	private String name;
	private BigDecimal price;
	private Category category;
}
