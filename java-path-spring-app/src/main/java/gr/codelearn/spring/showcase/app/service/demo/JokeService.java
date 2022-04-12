package gr.codelearn.spring.showcase.app.service.demo;

public interface JokeService {
	String get(String[] categories, String... blacklistFlags);
}
