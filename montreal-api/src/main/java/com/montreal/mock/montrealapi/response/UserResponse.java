package com.montreal.mock.montrealapi.response;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.montreal.mock.montrealapi.converts.UserConvert;
import com.montreal.mock.montrealapi.dto.UserDTO;
import com.montreal.mock.montrealapi.model.UserPO;

@Component
public class UserResponse {

	@Autowired
	private UserConvert convert;

	public ResponseEntity<UserDTO> responseUser(UserPO usuario) {
		if (!Optional.ofNullable(usuario).isPresent()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(convert.convertToDto(Optional.ofNullable(usuario)));
		}
	}

	public ResponseEntity<List<UserDTO>> responseUsers(List<UserPO> usuarios) {
		if (!Optional.ofNullable(usuarios).isPresent()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(convert.convertToListDto(Optional.ofNullable(usuarios)));
		}
	}

}
