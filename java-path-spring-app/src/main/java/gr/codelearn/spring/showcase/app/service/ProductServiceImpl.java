package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"products"}, keyGenerator = "customCacheKeyGenerator")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

	@Override
	@Cacheable
	public List<Product> findAll() {
		logger.debug("Product list does not exist in cache, fetching from repository.");
		return productRepository.findAll();
	}

	@Override
	@Cacheable("product")
	public Product findBySerial(String serial) {
		logger.debug("Product does not exist in cache, fetching from repository.");
		return productRepository.findBySerial(serial);
	}
}
