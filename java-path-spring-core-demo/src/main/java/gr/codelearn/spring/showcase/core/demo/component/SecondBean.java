package gr.codelearn.spring.showcase.core.demo.component;

import gr.codelearn.spring.showcase.core.demo.base.BaseComponent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SecondBean extends BaseComponent {
	@PostConstruct
	public void initExtra() {
		logger.debug(sayHello());
	}

	public String sayHello() {
		return String.format("Hello from %s.", getClass().getName());
	}
}
