package com.ChatPlateReader.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ChatPlateReader.dtos.ChatDto;
import com.ChatPlateReader.models.Chat;
import com.ChatPlateReader.services.ChatService;
import com.ChatPlateReader.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

@Controller
public class ChatController {

	
	@Autowired
	ChatService chatService;
	
	@Autowired
	UserService userService;
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	
	@MessageMapping("/chat")
    public void sendMessage(@Payload ChatDto message) {
        messagingTemplate.convertAndSendToUser(
           message.receiver(), "/queue/messages", message
        );
    }
	
	@Operation(description = "Busca chat pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Chat> findById(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(chatService.findById(id));
	}
	
	
	@Operation(description = "Busca chat pelo ID do usuario")
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Chat>> findByUser(@PathVariable(name = "id")UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(chatService.findByUser(id));
	}
	
	@Operation(description = "Lista todos os chats")
	@GetMapping
	public ResponseEntity<List<Chat>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(chatService.findAll());
	}
	
	@Operation(description = "Cria um chat")
	@PostMapping
	public ResponseEntity<Chat> createChat(@RequestBody ChatDto chatDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createChat(chatDto));
	}
	@Operation(description = "Deleta um chat")
	@DeleteMapping("/{id}")
	public ResponseEntity<Chat> deleteChat(@PathVariable(name = "id")UUID id){
		chatService.deleteChat(id);
		return ResponseEntity.noContent().build();
	}
}
