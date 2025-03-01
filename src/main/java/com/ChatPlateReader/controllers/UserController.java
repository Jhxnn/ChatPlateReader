package com.ChatPlateReader.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChatPlateReader.dtos.AuthDto;
import com.ChatPlateReader.dtos.UserRequestDto;
import com.ChatPlateReader.dtos.UserResponseDto;
import com.ChatPlateReader.infra.security.TokenService;
import com.ChatPlateReader.repositories.UserRepository;
import com.ChatPlateReader.services.EmailService;
import com.ChatPlateReader.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenService tokenService;
	
	
	@Operation(description = "Lista todos os usuarios")
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@Operation(description = "Lista usuario pelo id")
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}
	
	@Operation(description = "Logar usuario")
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid AuthDto authDto) {
		return ResponseEntity.ok().body(userService.returnToken(authDto));
	}
	
	@Operation(description = "Registrar usuario")
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@RequestBody @Valid  UserRequestDto userRequestDto) {
		return ResponseEntity.ok().body(userService.createUser(userRequestDto));
	}

	@Operation(description = "Atualizar usuario")
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable(name = "id")UUID id,
			@RequestBody UserRequestDto userRequestDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, userRequestDto));
	}
	
	@Operation(description = "Deletar usuario")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(name = "id")UUID id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}