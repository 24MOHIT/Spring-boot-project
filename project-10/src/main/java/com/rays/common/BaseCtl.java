package com.rays.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rays.dto.UserDTO;

public class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	@Autowired
	public S service;

	@PostMapping("save")
	public ORSResponse save(@RequestBody F form) {

		ORSResponse res = new ORSResponse();

		T dto = (T) form.getDto();

		UserDTO d = (UserDTO) dto;

		System.out.println("first name : " + d.getFirstName());

		System.out.println("last name : " + d.getLastName());

		if (dto.getId() != null && dto.getId() > 0) {
			service.update(dto);
			res.addMessage("User Update");
		} else {
			service.add(dto);
			res.addMessage("User Add");
		}
		return res;
	}
}