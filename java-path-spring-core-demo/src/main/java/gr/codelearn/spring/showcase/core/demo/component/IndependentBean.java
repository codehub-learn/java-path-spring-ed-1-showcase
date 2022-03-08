package gr.codelearn.spring.showcase.core.demo.component;

import gr.codelearn.spring.showcase.core.demo.base.BaseComponent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class IndependentBean extends BaseComponent {
	@PostConstruct
	public void sayHello() {
		// Code that will be executed after the initialization of the bean
		logger.info("Hello, I am an independent bean.");
	}

	@PreDestroy
	public void sayBye() {
		// Code that will be executed before the bean is destroyed
		logger.info("Bye bye, I used to be an independent bean.");
	}
}
