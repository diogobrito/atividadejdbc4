package com.atividade4.dao;

import java.util.List;

public interface Dao<T> {
	void add(T entity);
	List<T> list();
	void update(T entity);
	void remove(T entity);
	T getOne(int id);
}
