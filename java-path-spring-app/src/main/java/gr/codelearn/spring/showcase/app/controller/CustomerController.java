package gr.codelearn.spring.showcase.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController extends AbstractController<Customer> {
	private final CustomerService customerService;

	@Override
	protected BaseService<Customer, Long> getBaseService() {
		return customerService;
	}

	@GetMapping(params = {"email"})
	public ResponseEntity<ApiResponse<Customer>> findByEmail(@RequestParam String email) {
		return new ResponseEntity<>(ApiResponse.<Customer>builder().data(customerService.findByEmail(email)).build(),
									HttpStatus.OK);
	}

	@GetMapping(value = "filtered/{id}")
	public ResponseEntity<ApiResponse<JsonNode>> filteredCustomer(@PathVariable Long id) {
		Customer customer = customerService.get(id);
		JsonNode customerAsJsonNode = filterCustomer(customer,  "firstname", "lastname");
		return new ResponseEntity<>(ApiResponse.<JsonNode>builder().data(customerAsJsonNode).build(), HttpStatus.OK);
	}

	private JsonNode filterCustomer(Customer customer, String... fields) {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(fields);
		FilterProvider filters = new SimpleFilterProvider().addFilter("customerFilter", filter);
		objectMapper.setFilterProvider(filters);
		ObjectWriter objectWriter = objectMapper.writer();
		try {
			String customerAsString = objectWriter.writeValueAsString(customer);
			return objectMapper.readTree(customerAsString);
		} catch (JsonProcessingException e) {
			logger.error("Something went wrong during filtering", e);
		}
		return null;
	}
}
