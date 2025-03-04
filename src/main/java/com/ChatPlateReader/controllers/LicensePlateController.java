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

import com.ChatPlateReader.dtos.LicensePlateDto;
import com.ChatPlateReader.models.LicensePlate;
import com.ChatPlateReader.services.LicensePlateService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/licensePlate")
public class LicensePlateController {

    @Autowired
    LicensePlateService licensePlateService;

    @Operation(description = "Busca placa pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<LicensePlate> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(licensePlateService.findById(id));
    }

    @Operation(description = "Lista todas as placas")
    @GetMapping
    public ResponseEntity<List<LicensePlate>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(licensePlateService.findAll());
    }
    
    @Operation(description = "Busca placa pelo ID")
    @GetMapping("/message/{id}")
    public ResponseEntity<List<LicensePlate>> findByMessage(@PathVariable(name = "id")UUID id){
    	return ResponseEntity.status(HttpStatus.OK).body(licensePlateService.findByMessage(id));
    }

    @Operation(description = "Cria placa")
    @PostMapping
    public ResponseEntity<LicensePlate> createLicensePlate(@RequestBody LicensePlateDto licensePlateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(licensePlateService.createLicensePlate(licensePlateDto));
    }

    @Operation(description = "Deleta uma placa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicensePlate(@PathVariable(name = "id") UUID id) {
        licensePlateService.deleteLicensePlate(id);
        return ResponseEntity.noContent().build();
    }
}
