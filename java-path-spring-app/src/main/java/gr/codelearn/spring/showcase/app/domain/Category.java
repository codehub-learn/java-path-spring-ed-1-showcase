package gr.codelearn.spring.showcase.app.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Category extends BaseModel {
	private String description;
}
