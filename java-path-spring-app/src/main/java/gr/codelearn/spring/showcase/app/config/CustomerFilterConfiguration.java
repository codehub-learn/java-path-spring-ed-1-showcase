package gr.codelearn.spring.showcase.app.config;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerFilterConfiguration {
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonFilterCustomizer() {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("email");
		FilterProvider filters = new SimpleFilterProvider().addFilter("customerFilter", filter);

		// The following was replaced by a lambda expression:
		/*
		return new Jackson2ObjectMapperBuilderCustomizer(){
			@Override
			public void customize(final Jackson2ObjectMapperBuilder builder) {
				builder.filters(filters);
			}
		};
		*/

		return builder -> builder.filters(filters);
	}
}
