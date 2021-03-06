package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesPerCustomerCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController extends AbstractController<Order> {
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

	@Secured("ROLE_ADMIN")
	@GetMapping(headers = "action=totalNumberAndCostOfPurchasesPerCustomerCategory")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategory() {
		return new ResponseEntity<>(ApiResponse.<List<PurchasesPerCustomerCategoryDto>>builder().data(orderService.findTotalNumberAndCostOfPurchasesPerCustomerCategory())
											   .build(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(headers = "action=averageOrderCostPerCustomer")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> averageOrderCostPerCustomer() {
		return new ResponseEntity<>(ApiResponse.<List<KeyValue<String, BigDecimal>>>builder().data(orderService.findAverageOrderCostPerCustomer()).build(),
									HttpStatus.OK);
	}

	@GetMapping(value = "v1")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategoryV1() {
		return getV1Content();
	}

	@GetMapping(value = "v2")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategoryV2() {
		return getV2Content();
	}

	@GetMapping(headers = "API_VERSION=1")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategoryHV1() {
		return getV1Content();
	}

	@GetMapping(headers = "API_VERSION=2")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategoryHV2() {
		return getV2Content();
	}

	@GetMapping(params = "version=1")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategoryPV1() {
		return getV1Content();
	}

	@GetMapping(params = "version=2")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategoryPV2() {
		return getV2Content();
	}

	@GetMapping(produces = "application/vnd.api-v1+json")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategoryPPV1() {
		return getV1Content();
	}

	@GetMapping(produces = "application/vnd.api-v2+json")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> totalNumberAndCostOfPurchasesPerCustomerCategoryPPV2() {
		return getV2Content();
	}

	private ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> getV1Content() {
		return getContent(orderService.findTotalNumberAndCostOfPurchasesPerCustomerCategory());
	}

	private ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> getV2Content() {
		return getContent(null);
	}

	private ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> getContent(
			List<PurchasesPerCustomerCategoryDto> input) {
		return new ResponseEntity<>(ApiResponse.<List<PurchasesPerCustomerCategoryDto>>builder().data(input).build(),
									HttpStatus.OK);
	}
}
