package com.montreal.mock.montrealauth.security.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.montreal.mock.montrealauth.exception.AppException;
import com.montreal.mock.montrealauth.model.PaperPO;
import com.montreal.mock.montrealauth.model.UserAccess;
import com.montreal.mock.montrealauth.model.UserPO;
import com.montreal.mock.montrealauth.repository.PaperRepository;
import com.montreal.mock.montrealauth.repository.UserAccessRepository;
import com.montreal.mock.montrealauth.repository.UserRepository;
import com.montreal.mock.montrealauth.response.ApiResponse;
import com.montreal.mock.montrealauth.response.JwtResponse;
import com.montreal.mock.montrealauth.security.JwtTokenProvider;
import com.montreal.mock.montrealauth.security.request.LoginForm;
import com.montreal.mock.montrealauth.security.request.SignUpForm;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/montreal-auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaperRepository paperRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserAccessRepository userAccessRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping(value = "/signin", produces = { "application/json" })
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		logger.info("Realizando tentativa de login");

		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		} catch (DisabledException e) {
			return new ResponseEntity(new ApiResponse(false, "user disabled"), HttpStatus.BAD_REQUEST);
		} catch (BadCredentialsException e) {
			return new ResponseEntity(new ApiResponse(false, "invalid credentials"), HttpStatus.BAD_REQUEST);
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtResponse(jwt));

	}

	@PostMapping(value = "/signup", produces = { "application/json" })
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		UserPO user = new UserPO(signUpRequest.getName(), signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()), true);

		PaperPO userRole = paperRepository.findByName(signUpRequest.getRole())
				.orElseThrow(() -> new AppException("User Role not set"));

		user.setPaper(userRole);

		UserPO userResult = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/montreal-api/users/{userName}")
				.buildAndExpand(userResult.getEmail()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}

	@DeleteMapping(value = "/logout")
	public ResponseEntity<?> removeToken(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		UserAccess userAccess = new UserAccess(token.replace("Bearer ", ""));
		userAccessRepository.save(userAccess);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/montreal-auth/signin").buildAndExpand(userAccess.getCodigo()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User logout with successfully"));		
	}


}