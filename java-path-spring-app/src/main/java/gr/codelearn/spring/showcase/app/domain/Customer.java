package gr.codelearn.spring.showcase.app.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
	private String email;
	private String firstname;
	private String lastname;
	private String address;
	private CustomerCategory customerCategory;
}
