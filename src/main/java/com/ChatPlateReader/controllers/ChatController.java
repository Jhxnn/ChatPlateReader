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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChatPlateReader.dtos.ChatDto;
import com.ChatPlateReader.models.Chat;
import com.ChatPlateReader.services.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {

	
	@Autowired
	ChatService chatService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Chat> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(chatService.findById(id));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Chat>> findByUser(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(chatService.findByUser(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Chat>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(chatService.findAll());
	}
	@PostMapping
	public ResponseEntity<Chat> createChat(@RequestBody ChatDto chatDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createChat(chatDto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Chat> deleteChat(@PathVariable(name = "id")UUID id){
		chatService.deleteChat(id);
		return ResponseEntity.noContent().build();
	}
}
