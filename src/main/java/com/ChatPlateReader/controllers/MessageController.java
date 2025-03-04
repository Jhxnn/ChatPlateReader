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

import com.ChatPlateReader.dtos.MessageDto;
import com.ChatPlateReader.models.Message;
import com.ChatPlateReader.services.MessageService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    
    @Operation(description = "Busca mensagem pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.findById(id));
    }

    @Operation(description = "Lista todas as mensagens")
    @GetMapping
    public ResponseEntity<List<Message>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.findAll());
    }
    
    
    @Operation(description = "Busca mensagens pelo ID do chat")
    @GetMapping("/chat/{id}")
    public ResponseEntity<List<Message>> findByChat(@PathVariable(name = "id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(messageService.findByChat(id));
    }
    
    @Operation(description = "Cria uma mensagem")
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageDto messageDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.createMessage(messageDto));
    }

    
    @Operation(description = "Deleta uma mensagem")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable(name = "id") UUID id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
