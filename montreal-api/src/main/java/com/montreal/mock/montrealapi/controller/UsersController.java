package com.montreal.mock.montrealapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.montreal.mock.montrealapi.api.UsersApi;
import com.montreal.mock.montrealapi.dto.UserDTO;
import com.montreal.mock.montrealapi.response.UserResponse;
import com.montreal.mock.montrealapi.service.UserService;

@Controller
@RequestMapping("/montreal-api/users")
public class UsersController implements UsersApi {

	@Autowired
	private UserResponse response;

	@Autowired
	private UserService service;

	@Override
	public ResponseEntity<List<UserDTO>> getUsers() {
		return response.responseUsers(service.getUsers());
	}

	@Override
	public ResponseEntity<UserDTO> getUserToUsername(
			@PathVariable(value = "username", required = true) String username) {
		return response.responseUser(service.getUserToUsername(username));
	}

}
