package com.rays.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropDownList;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentService;
import com.rays.service.RoleService;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl {

	@Autowired
	public UserService userService;
	
	@Autowired
	public AttachmentService attachmentService;
	
	@Autowired
	public RoleService roleService;
	
	@GetMapping("preload")
	public ORSResponse preload() {
		
		ORSResponse res = new ORSResponse();
		
		List<DropDownList> roleList = roleService.search(null, 0, 0);
		
		res.addResult("roleList", roleList);
		
		return res;
		
	}

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			userService.update(dto);
			res.addData(dto.getId());
			res.addMessage("User Updated Successfully..!!");
		} else {
			long pk = userService.add(dto);
			res.addData(pk);
			res.addMessage("User added Successfully..!!");
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = userService.findById(id);

		res.addData(dto);

		return res;
	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			userService.delete(id);
		}

		res.addMessage("User deleted successfully");

		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());

		List list = userService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Result not found...!!!");
		} else {
			res.addData(list);
		}
		return res;
	}
	
	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file, HttpServletRequest req) {
		
		AttachmentDTO attachmentDTO = new AttachmentDTO(file);
		
		attachmentDTO.setDescription("profile pic");
		
		attachmentDTO.setUserId(userId);
		
		UserDTO userDTO = userService.findById(userId);
		
		if (userDTO.getImageId() != null && userDTO.getImageId() > 0) {
			
			attachmentDTO.setId(userDTO.getImageId());
			
		}
		
		Long imageId = attachmentService.save(attachmentDTO);
		
		if (userDTO.getImageId() == null) {
			userDTO.setImageId(imageId);
			userService.update(userDTO);
		}
		
		ORSResponse res = new ORSResponse();
		
		res.addResult("imageId", imageId);
		
		return res;
		
	}
}