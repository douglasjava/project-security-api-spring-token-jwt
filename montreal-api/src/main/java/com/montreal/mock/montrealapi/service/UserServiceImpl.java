package com.montreal.mock.montrealapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.montreal.mock.montrealapi.model.PaperPO;
import com.montreal.mock.montrealapi.model.UserPO;
import com.montreal.mock.montrealapi.repository.UserRepository;

@Service
@Qualifier(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<UserPO> getUsers() {
		return repository.findAll();
	}

	@Override
	public void createUser() {
		UserPO usuario = new UserPO();
		usuario.setNome("Douglas Dias");
		usuario.setEmail("douglas.dias@montreal.com.br");
		usuario.setPassword("$2a$10$H27XEvvpZwSA2CxSVIt7eOF5BIHdTb8DrpJ1hYXCd/JGSS3Kq.QzC");
		usuario.setAtivo(true);
		usuario.setPapel(new PaperPO("Administrador"));
		repository.save(usuario);
	}

}
