package gr.codelearn.spring.showcase.core.demo.config;

import gr.codelearn.spring.showcase.core.demo.base.BaseComponent;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:global-${spring.profiles.active}.properties") // Custom properties file
@ConfigurationProperties(prefix = "app") // all app.* properties
@Data
public class GlobalProperties extends BaseComponent {
	private int threadPool;
	private String email;
	private List<String> errorCodes;
}
