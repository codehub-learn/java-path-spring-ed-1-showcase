package gr.codelearn.spring.showcase.core.demo.bootstrap;

import gr.codelearn.spring.showcase.core.demo.base.BaseComponent;
import gr.codelearn.spring.showcase.core.demo.component.LazyBean;
import gr.codelearn.spring.showcase.core.demo.component.SecondBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class TestRunner extends BaseComponent implements CommandLineRunner {
	private final SecondBean secondBean;
	private final LazyBean lazyBean;

	@Lazy
	public TestRunner(LazyBean lazyBean, SecondBean secondBean) {
		this.lazyBean = lazyBean;
		this.secondBean = secondBean;
	}

	@Override
	public void run(final String... args) throws Exception {
		logger.debug("Check to see if we can get feedback from injected bean: {}.", secondBean.sayHello());
		for (final String arg : args) {
			logger.trace("{}.", arg);
		}

		logger.debug("Getting feedback from target bean: {}", lazyBean.sayHello());
	}
}
