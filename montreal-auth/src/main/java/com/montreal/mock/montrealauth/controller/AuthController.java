package com.montreal.mock.montrealauth.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.montreal.mock.montrealapi.dto.UserDTO;
import com.montreal.mock.montrealapi.model.UserPO;
import com.montreal.mock.montrealauth.security.JwtTokenProvider;
import com.montreal.mock.montrealauth.security.UserPrincipal;

@Controller
@RequestMapping("/montreal-auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping(value = "/login",  produces = { "application/json" })
	public ResponseEntity<UserDTO> authenticateUser(@Valid @RequestBody UserPO loginRequest) {

		logger.info("Realizando tentativa de login");

		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		UserDTO usuario = new UserDTO();
		UserPrincipal userPrincipal = null;
		userPrincipal = (UserPrincipal) authentication.getPrincipal();

		usuario.setNome(userPrincipal.getName());
		usuario.setEmail(userPrincipal.getEmail());
		usuario.setAccessToken(jwt);
		usuario.setTokenType("Bearer");

		return ResponseEntity.ok(usuario);

	}

}