package gr.codelearn.spring.showcase.app.controller.demo;

import gr.codelearn.spring.showcase.app.service.demo.JokeService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class JokeController {
	private final JokeService jokeServiceImpl;

	@GetMapping
	public ResponseEntity<ApiResponse<String>> get() {
		return ResponseEntity.ok(ApiResponse.<String>builder()
											.data(jokeServiceImpl.get(new String[]{"Programming", "Christmas"}, "nsfw",
																	  "religious", "political", "racist", "sexist",
																	  "explicit")).build());
	}
}
