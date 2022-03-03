package gr.codelearn.spring.showcase.core.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
	private String serial;
	private String name;
	private BigDecimal price;
}
