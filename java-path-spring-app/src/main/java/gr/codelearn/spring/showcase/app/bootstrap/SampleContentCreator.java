package gr.codelearn.spring.showcase.app.bootstrap;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.CustomerCategory;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleContentCreator extends BaseComponent implements CommandLineRunner {
	private final CustomerService customerService;

	@Override
	public void run(final String... args) throws Exception {
		Customer customer = Customer.builder().firstname("John").lastname("Pappas").customerCategory(
				CustomerCategory.BUSINESS).id(1L).build();
		logger.debug("Created sample customer {}.", customer);

		customerService.create(customer);
	}
}
