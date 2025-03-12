package com.ChatPlateReader.controllers;

import java.util.List;
import java.util.UUID;

import com.ChatPlateReader.dtos.MessageDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ChatPlateReader.dtos.MessageDto;
import com.ChatPlateReader.models.Message;
import com.ChatPlateReader.services.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;

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



    @Operation(description = "Manda mensagem")
    @PostMapping(value = "/send/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> messageTest(
            @PathVariable(name = "userId")UUID userId,  // Pega o UUID da URL
            @RequestPart("data") MessageDataDto data, // JSON
            @RequestPart("image") MultipartFile image // Arquivo
    ) {
        MessageDto messageDto = new MessageDto(userId, data.content(), image, data.type());
        return ResponseEntity.status(HttpStatus.OK).body(messageService.ocrTest(messageDto));
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
