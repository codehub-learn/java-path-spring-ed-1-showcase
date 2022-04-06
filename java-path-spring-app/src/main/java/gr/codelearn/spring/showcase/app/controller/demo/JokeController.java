package gr.codelearn.spring.showcase.app.controller.demo;

import gr.codelearn.spring.showcase.app.service.demo.JokeServiceImpl;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restTemplate")
@RequiredArgsConstructor
public class JokeController {
	private final JokeServiceImpl jokeServiceImpl;

	@GetMapping
	public ResponseEntity<ApiResponse<String>> get() {
		// potentially the categories could be fetched from the user
		return new ResponseEntity<>(ApiResponse.<String>builder()
											   .data(jokeServiceImpl.get(new String[]{"Programming", "Christmas"},
																		 "nsfw", "religious", "political", "racist",
																		 "sexist", "explicit")).build(), HttpStatus.OK);
	}
}
