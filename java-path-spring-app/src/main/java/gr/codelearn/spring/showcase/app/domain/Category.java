package gr.codelearn.spring.showcase.app.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
	private String description;
}
