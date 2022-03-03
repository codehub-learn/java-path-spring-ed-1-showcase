package gr.codelearn.spring.showcase.core.demo.bootstrap;

import gr.codelearn.spring.showcase.core.demo.base.BaseComponent;
import gr.codelearn.spring.showcase.core.demo.component.SecondBean;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestRunner extends BaseComponent implements CommandLineRunner {
	private final SecondBean secondBean;

	@Override
	public void run(final String... args) throws Exception {
		logger.debug("Check to see if we can get feedback from injected bean: {}.", secondBean.sayHello());
		for (final String arg : args) {
			logger.trace("{}.", arg);
		}
	}
}
