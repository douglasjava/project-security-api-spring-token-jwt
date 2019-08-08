package com.montreal.mock.montrealapi.converts;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.montreal.mock.montrealapi.dto.UserDTO;
import com.montreal.mock.montrealapi.exceptions.HttpInternalException;
import com.montreal.mock.montrealapi.model.UserPO;

@Component
public class UserConvert {

	private static final Logger logger = LoggerFactory.getLogger(UserConvert.class);

	public List<UserDTO> convertToListDto(Optional<List<UserPO>> usersOptional) {
		return usersOptional.orElse(emptyList()).stream().map(u -> convertToDto(Optional.ofNullable(u)))
				.collect(toList());
	}

	public UserDTO convertToDto(Optional<UserPO> entityOpt) {

		UserDTO usuarioDTO = new UserDTO();
		try {

			if (entityOpt.isPresent()) {
				UserPO entity = entityOpt.get();
				usuarioDTO.setId(entity.getId().toString());
				usuarioDTO.setNome(entity.getName());
				usuarioDTO.setEmail(entity.getEmail());
				usuarioDTO.setAtivo(entity.isAtivo());
			}

		} catch (Exception e) {
			logger.error("Failed to convert userPO to userDTO");
			throw new HttpInternalException("Failed to convert userPO to userDTO ==> " + e.getMessage());
		}
		return usuarioDTO;
	}

}
