package com.ChatPlateReader.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ChatPlateReader.dtos.DocumentDto;
import com.ChatPlateReader.models.Document;
import com.ChatPlateReader.repositories.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	DocumentRepository documentRepository;

	public List<Document> findAll() {
		return documentRepository.findAll();
	}

	public Document findById(UUID id) {
		return documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
	}

	public Document createDocument(DocumentDto documentDto) {
		var document = new Document();
		BeanUtils.copyProperties(documentDto, document);
		return documentRepository.save(document);
	}

	public void deleteDocument(UUID id) {
		var document = findById(id);
		documentRepository.delete(document);
	}
}