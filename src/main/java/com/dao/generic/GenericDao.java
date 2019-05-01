package com.dao.generic;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T> {
	void save(T entity);

	T getById(Long id);
	T getByName(String name);

	List<T> getAll();

	void update(T entity);

	void deleteById(Long id);
}
