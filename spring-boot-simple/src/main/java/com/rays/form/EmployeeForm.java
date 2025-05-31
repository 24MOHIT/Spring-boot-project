package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.EmployeeDTO;

public class EmployeeForm extends BaseForm {

	@NotEmpty(message = "Name is Required")
	private String name;
	
	@NotEmpty(message = "LoginId is Required")
	private String loginId;
	
	@NotEmpty(message = "Post is Required")
	private String post;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	
	@Override
	public BaseDTO getDto() {
		
		EmployeeDTO dto = (EmployeeDTO) initDTO(new EmployeeDTO());
		
		dto.setName(name);
		dto.setLoginId(loginId);
		dto.setPost(post);
		
		return dto ;
	}
}
