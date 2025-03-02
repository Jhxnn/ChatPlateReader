package com.ChatPlateReader.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ChatPlateReader.dtos.LicensePlateDto;
import com.ChatPlateReader.models.LicensePlate;
import com.ChatPlateReader.repositories.LicensePlateRepository;

@Service
public class LicensePlateService {

	@Autowired
	LicensePlateRepository licensePlateRepository;
	
	@Autowired
	MessageService messageService;

	public List<LicensePlate> findAll() {
		return licensePlateRepository.findAll();
	}

	public LicensePlate findById(UUID id) {
		return licensePlateRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
	}
	
	public List<LicensePlate> findByMessage(UUID messageId){
		var message = messageService.findById(messageId);
		return licensePlateRepository.findByMessage(message);
	}

	public LicensePlate createLicensePlate(LicensePlateDto licensePlateDto) {
		var licensePlate = new LicensePlate();
		BeanUtils.copyProperties(licensePlateDto, licensePlate);
		return licensePlateRepository.save(licensePlate);
	}

	public void deleteLicensePlate(UUID id) {
		var licensePlate = findById(id);
		licensePlateRepository.delete(licensePlate);
	}
}
