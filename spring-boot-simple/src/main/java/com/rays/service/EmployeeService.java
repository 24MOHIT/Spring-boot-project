package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.EmployeeDAO;
import com.rays.dto.EmployeeDTO;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	public EmployeeDAO dao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long add(EmployeeDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(EmployeeDTO dto) {
		dao.update(dto);
	}
	
	@Transactional(readOnly = true)
	public EmployeeDTO findById(long pk) {
		EmployeeDTO dto = dao.findByPk(pk);
		return dto;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeDTO delete(long id) {
		EmployeeDTO dto = findById(id);
		return dto;
		
	}
	
	@Transactional(readOnly = true)
	public List search(EmployeeDTO dto, int pageNo, int pageSize) {
		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}
	
}
