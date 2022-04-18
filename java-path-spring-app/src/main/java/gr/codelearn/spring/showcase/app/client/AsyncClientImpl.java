package gr.codelearn.spring.showcase.app.client;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.transfer.ApiError;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class AsyncClientImpl extends BaseComponent implements AsyncClient {
	private static final String ORDER_ENDPOINT = "http://localhost:8080/orders";
	private static final String CUSTOMER_ENDPOINT = "http://localhost:8080/customers";
	private static final String PRODUCT_ENDPOINT = "http://localhost:8080/products";

	private final RestTemplate restTemplate;

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<Order>> retrieveOrders() throws InterruptedException {
		try {
			//@formatter:off
			ResponseEntity<ApiResponse<List<Order>>> responseEntity = restTemplate.exchange(
				ORDER_ENDPOINT,
				HttpMethod.GET,
				new HttpEntity<>(getHttpHeaders()),
				new ParameterizedTypeReference<>() {});
			//@formatter:on

			checkForErrors(responseEntity);
			List<Order> orders = Objects.requireNonNull(responseEntity.getBody().getData());
			logger.debug("Retrieved {} orders.", orders.size());

			Thread.sleep(2000);

			return CompletableFuture.completedFuture(orders);
		} catch (RestClientException rce) {
			logger.error("Unable to complete the service call", rce);
		}
		return null;
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<Customer>> retrieveCustomers() throws InterruptedException {
		try {
			//@formatter:off
			ResponseEntity<ApiResponse<List<Customer>>> responseEntity = restTemplate.exchange(
					CUSTOMER_ENDPOINT,
					HttpMethod.GET,
					new HttpEntity<>(getHttpHeaders()),
					new ParameterizedTypeReference<>() {});
			//@formatter:on

			checkForErrors(responseEntity);
			List<Customer> customers = Objects.requireNonNull(responseEntity.getBody().getData());
			logger.debug("Retrieved {} customers.", customers.size());

			Thread.sleep(2000);

			return CompletableFuture.completedFuture(customers);
		} catch (RestClientException rce) {
			logger.error("Unable to complete the service call", rce);
		}
		return null;
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<Product>> retrieveProducts() throws InterruptedException {
		try {
			//@formatter:off
			ResponseEntity<ApiResponse<List<Product>>> responseEntity = restTemplate.exchange(
					PRODUCT_ENDPOINT,
					HttpMethod.GET,
					new HttpEntity<>(getHttpHeaders()),
					new ParameterizedTypeReference<>() {});
			//@formatter:on

			checkForErrors(responseEntity);
			List<Product> products = Objects.requireNonNull(responseEntity.getBody().getData());
			logger.debug("Retrieved {} products.", products.size());

			Thread.sleep(2000);

			return CompletableFuture.completedFuture(products);
		} catch (RestClientException rce) {
			logger.error("Unable to complete the service call", rce);
		}
		return null;
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

	private <T> void checkForErrors(final ResponseEntity<ApiResponse<T>> response) {
		if (response.getStatusCodeValue() >= 300 || Objects.requireNonNull(response.getBody()).getApiError() != null) {
			ApiError apiError = response.getBody().getApiError();
			throw new RestClientException(
					String.format("Service returned error with status code %d. Message: %s", apiError.getStatus(),
								  apiError.getMessage()));
		}
		if (response.getBody().getData() == null) {
			throw new RestClientException(
					String.format("Service returned successfully with status code %d without any data.",
								  response.getStatusCodeValue()));
		}

	}
}
