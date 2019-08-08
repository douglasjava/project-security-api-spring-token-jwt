package com.montreal.mock.montrealauth.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.montreal.mock.montrealauth.dto.UserDTO;

public interface UsersApi {

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<List<UserDTO>> getUsers();

	@GetMapping(value = "/{username}", produces = { "application/json" })
	public ResponseEntity<UserDTO> getUserToUsername(@PathVariable("username") String username);

}
