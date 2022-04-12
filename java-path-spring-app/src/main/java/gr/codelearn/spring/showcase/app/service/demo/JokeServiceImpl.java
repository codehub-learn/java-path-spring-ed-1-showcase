package gr.codelearn.spring.showcase.app.service.demo;

import com.fasterxml.jackson.databind.JsonNode;
import gr.codelearn.spring.showcase.app.base.BaseComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class JokeServiceImpl extends BaseComponent implements JokeService {
	private final RestTemplate restTemplate;
	private final String URL_TEMPLATE = "https://v2.jokeapi.dev/joke/";

	@Override
	public String get(String[] categories, String... blacklistFlags) {
		String url = prepareUrl(categories, blacklistFlags);
		ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
		JsonNode body = response.getBody();
		return body.get("joke").asText();
	}

	private String prepareUrl(String[] categories, String... blacklistFlags) {
		//@formatter:off
		return
				UriComponentsBuilder
						.fromUriString(URL_TEMPLATE)
						.pathSegment(String.join(",", categories))
						.queryParam("blacklistFlags", String.join(",", blacklistFlags))
						.queryParam("type", "single")
						.build()
						.toString();
		//@formatter:on
	}

}
