package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	public D dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(T dto) {
		long pk = dao.add(dto);
		return pk;
	}

	public void update(T dto) {

	}

	public void delete(T dto) {

	}

	public T findByPk(long pk) {
		return null;
	}

	public List search(T dto, int pageNo, int pageSize) {
		return null;
	}
}