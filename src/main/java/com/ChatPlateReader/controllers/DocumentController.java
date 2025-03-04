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

import com.ChatPlateReader.dtos.DocumentDto;
import com.ChatPlateReader.models.Document;
import com.ChatPlateReader.services.DocumentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    
    @Operation(description = "Busca documento pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Document> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.findById(id));
    }

    @Operation(description = "Lista todos os documentos")
    @GetMapping
    public ResponseEntity<List<Document>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.findAll());
    }
    
    @Operation(description = "Busca documento pela mensagem")
    @GetMapping("/message/{id}")
    public ResponseEntity<List<Document>> findByMessage(@PathVariable(name = "id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(documentService.findByMessage(id));
    }

    @Operation(description = "Cria um documento")
    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody DocumentDto documentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.createDocument(documentDto));
    }

    @Operation(description = "Deleta um documento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable(name = "id") UUID id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
