package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T, Long> {
	public abstract JpaRepository<T, Long> getRepository();

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public T create(T item) {
		return getRepository().save(item);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public List<T> createAll(List<T> items) {
		return getRepository().saveAll(items);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public List<T> createAll(T... items) {
		return createAll(Arrays.asList(items));
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(T item) {
		getRepository().save(item);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void delete(T item) {
		getRepository().delete(item);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void deleteById(Long id) {
		getRepository().deleteById(id);
	}

	@Transactional(readOnly = true)
	public T get(Long id) {
		return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Transactional(readOnly = true)
	public boolean exists(T item) {
		return getRepository().existsById(item.getId());
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return getRepository().findAll();
	}
}
