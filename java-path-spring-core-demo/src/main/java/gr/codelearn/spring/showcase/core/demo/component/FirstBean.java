package gr.codelearn.spring.showcase.core.demo.component;

import gr.codelearn.spring.showcase.core.demo.base.BaseComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class FirstBean extends BaseComponent {
	private final SecondBean secondBean;

	@PostConstruct
	public void runSomeTests() {
		logger.debug("Received the content '{}' from the other bean.", secondBean.sayHello());
	}
}
