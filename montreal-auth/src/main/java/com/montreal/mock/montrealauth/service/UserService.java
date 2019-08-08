package com.montreal.mock.montrealauth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.montreal.mock.montrealauth.exception.HttpBadRequestException;
import com.montreal.mock.montrealauth.model.PaperPO;
import com.montreal.mock.montrealauth.model.UserPO;
import com.montreal.mock.montrealauth.model.enums.RoleName;
import com.montreal.mock.montrealauth.repository.UserRepository;

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
