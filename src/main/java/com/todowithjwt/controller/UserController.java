package com.todowithjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todowithjwt.entity.AuthRequest;
import com.todowithjwt.entity.User;
import com.todowithjwt.service.impl.JwtService;
import com.todowithjwt.service.impl.UserInfoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	private UserInfoService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}
	
	@PostMapping("/addNewUser")
	public String addNewUser(@RequestBody @Valid User user) {
		return service.addUser(user);
	}

//	@GetMapping("/user/userProfile")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public String userProfile() {
//		return "Welcome to User Profile";
//	}
//
//	@GetMapping("/admin/adminProfile")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public String adminProfile() {
//		return "Welcome to Admin Profile";
//	}

//	@GetMapping("/welcome")
//	public String welcome() {
//		return "Welcome this endpoint is not secure";
//	}
}