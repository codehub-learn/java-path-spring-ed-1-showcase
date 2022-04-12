package gr.codelearn.spring.showcase.app.controller.demo;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.CustomerCategory;
import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.service.ProductService;
import gr.codelearn.spring.showcase.app.service.demo.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("mvc")
@AllArgsConstructor
public class MvcController extends BaseComponent {
	private final JokeService jokeServiceImpl;
	private final ProductService productService;
	private final OrderService orderService;
	private final CustomerService customerService;

	@GetMapping
	public String index(Model model) {
		String joke = jokeServiceImpl.get(new String[]{"Programming", "Christmas"}, "nsfw", "religious", "political",
										  "racist", "sexist", "explicit");
		model.addAttribute("joke", joke);
		return "home";
	}

	@GetMapping("products")
	public String products(Model model) {
		List<Product> allProducts = productService.findAll();
		model.addAttribute("allProducts", allProducts);
		return "products";
	}

	@GetMapping("findOrder")
	public String findOrder(Model model, @RequestParam(value = "orderId", required = false) Long orderId) {
		if (orderId != null) {
			try {
				Order order = orderService.getLazy(orderId);
				model.addAttribute("order", order);
			} catch (NoSuchElementException e) {
				logger.info("Order ID does not exist", e);
				model.addAttribute("error", "Order does not exist.");
			}
		}
		return "findOrder";
	}

	@GetMapping("about")
	public String about(HttpServletRequest request, HttpServletResponse response) {
		setupCounter(request, response);
		return "about";
	}

	@GetMapping("registerCustomer")
	public String registerCustomer(Model model) {
		model.addAttribute("customerCategories", CustomerCategory.values());
		return "registerCustomer";
	}

	@PostMapping("registerCustomer")
	public String registerCustomer(@ModelAttribute("customer") Customer customer, HttpSession session) {
		// remember that there is no validation occuring here, and if an exception happens, the exception handler
		// will handle it, check the presentations on how to validate a form coming from the front-end
		customer = customerService.create(customer);
		// adds the ID to the session:
		// (example that was not shown in class but good to know how to do)
		session.setAttribute("registeredCustomerID", customer.getId());
		return "redirect:/mvc/registerCustomer";
	}

	private void setupCounter(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		Cookie counterCookie;
		Optional<Cookie> counterCookieOptional = Arrays.stream(cookies).filter(
				cookie -> cookie.getName().equals("counter")).findFirst();
		if (counterCookieOptional.isPresent()) {
			counterCookie = counterCookieOptional.get();
			String valueStr = counterCookie.getValue();
			int value = Integer.parseInt(valueStr);
			value++;
			counterCookie.setValue(String.valueOf(value));
		} else {
			counterCookie = new Cookie("counter", String.valueOf(0));
		}
		response.addCookie(counterCookie);
	}
}
