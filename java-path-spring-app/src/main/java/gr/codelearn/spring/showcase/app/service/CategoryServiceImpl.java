package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Category;
import gr.codelearn.spring.showcase.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}

	@Override
	@Cacheable(cacheNames = "categories")
	public List<Category> findAll() {
		logger.info("Category list does not exist in cache, fetching from repository.");
		return categoryRepository.findAll();
	}

	@Override
	@Cacheable(cacheNames = "category", key = "#id")
	public Category get(Long id) {
		logger.info("Category does not exist in cache, fetching from repository.");
		return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	@Cacheable(cacheNames = "category")
	public Category findByDescription(String description) {
		logger.info("Category does not exist in cache, fetching from repository.");
		return categoryRepository.findByDescription(description);
	}
}
