package com.montreal.mock.montrealapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.montreal.mock.montrealapi.exceptions.HttpBadRequestException;
import com.montreal.mock.montrealapi.model.PaperPO;
import com.montreal.mock.montrealapi.model.UserPO;
import com.montreal.mock.montrealapi.model.enums.RoleName;
import com.montreal.mock.montrealapi.repository.UserRepository;

@Service
@Qualifier(value = "userServiceImpl")
public class UserService {

	@Autowired
	private UserRepository repository;

	
	public List<UserPO> getUsers() {
		return repository.findAll();
	}

	public UserPO getUserToUsername(String username) {
		return repository.findByEmail(username)
				.orElseThrow(() -> new HttpBadRequestException("Not found user with username: " + username));
	}

	public void createUser() {

		UserPO usuario = new UserPO();
		usuario.setName("Douglas Dias");
		usuario.setEmail("douglas.dias@montreal.com.br");
		usuario.setPassword("$2a$10$h75SGj6rlbtJsg8BKXpt0.SGPTsO3nEQ423izxBSb156O26VtSO8S");
		usuario.setAtivo(true);
		usuario.setPaper(new PaperPO(RoleName.ROLE_ADMIN.getDescricao()));
		repository.save(usuario);

	}
	
}
