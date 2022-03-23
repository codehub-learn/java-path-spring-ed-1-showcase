package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T, Long> {
	public abstract JpaRepository<T, Long> getRepository();

	public T create(T item) {
		return getRepository().save(item);
	}

	public List<T> createAll(List<T> items) {
		return getRepository().saveAll(items);
	}

	public List<T> createAll(T... items) {
		return createAll(Arrays.asList(items));
	}

	public void update(T item) {
		getRepository().save(item);
	}

	public void delete(T item) {
		getRepository().delete(item);
	}

	public void deleteById(Long id) {
		getRepository().deleteById(id);
	}

	public T get(Long id) {
		return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
	}

	public boolean exists(T item) {
		return getRepository().existsById(item.getId());
	}

	public List<T> findAll() {
		return getRepository().findAll();
	}
}
