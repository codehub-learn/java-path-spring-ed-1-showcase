package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController extends AbstractController<Order>{
	private final OrderService orderService;
	@Override
	protected BaseService<Order, Long> getBaseService() {
		return orderService;
	}
	@GetMapping
	@Override
	public ResponseEntity<ApiResponse<List<Order>>> findAll() {
		return new ResponseEntity<>(ApiResponse.<List<Order>>builder().data(orderService.findAllLazy()).build(),
									HttpStatus.OK);
	}
}
