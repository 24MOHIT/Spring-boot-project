package com.rays.common;

import java.util.List;

public interface BaseServiceInt<T extends BaseDTO> {

	public long add(T dto);

	public void update(T dto);

	public void delete(T dto);

	public T findByPk(long pk);

	public List search(T dto, int pageNo, int pageSize);
}