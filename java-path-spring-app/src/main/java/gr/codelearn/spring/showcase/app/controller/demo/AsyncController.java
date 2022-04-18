package gr.codelearn.spring.showcase.app.controller.demo;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.client.AsyncClient;
import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class AsyncController extends BaseComponent {
	private final AsyncClient asyncClient;

	@GetMapping("/asynch")
	public void testAsync() throws InterruptedException, ExecutionException {
		logger.info("Test asynchronous calls is about to start.");
		CompletableFuture<List<Order>> ordersFuture = asyncClient.retrieveOrders();
		CompletableFuture<List<Customer>> customersFuture = asyncClient.retrieveCustomers();
		CompletableFuture<List<Product>> productsFuture = asyncClient.retrieveProducts();

		// Wait until they are all done.
		CompletableFuture.allOf(ordersFuture, customersFuture, productsFuture);

		logger.info("{} order(s) retrieved.", ordersFuture.get().size());
		logger.info("{} customer(s) retrieved.", customersFuture.get().size());
		logger.info("{} product(s) retrieved.", productsFuture.get().size());

		logger.info("Test asynchronous calls just finished.");
	}
}
