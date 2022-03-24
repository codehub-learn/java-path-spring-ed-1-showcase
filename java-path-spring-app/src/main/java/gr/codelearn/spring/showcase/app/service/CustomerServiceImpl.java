package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.CustomerCategory;
import gr.codelearn.spring.showcase.app.repository.CustomerRepository;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
	private final CustomerRepository customerRepository;

	@Override
	public JpaRepository<Customer, Long> getRepository() {
		return customerRepository;
	}

	@Override
	public Customer findByEmail(final String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<KeyValue<String, Long>> findCustomersPurchasedMostExpensiveProduct() {
		return customerRepository.findCustomersPurchasedMostExpensiveProduct();
	}

	@Override
	@Transactional(readOnly = true)
	public List<KeyValue<String, BigDecimal>> getStatistics() {
		//@formatter:off
		return List.of(
				new KeyValue<>("customerSize",
							   BigDecimal.valueOf(customerRepository.count())),
				new KeyValue<>("mostExpensiveCustomersSize",
							   BigDecimal.valueOf(customerRepository.findCustomersPurchasedMostExpensiveProduct().size())),
				new KeyValue<>("customerCategorySize",
							   BigDecimal.valueOf(CustomerCategory.values().length)));
		//@formatter:on
	}
}
