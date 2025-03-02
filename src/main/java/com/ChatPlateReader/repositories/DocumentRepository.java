package com.ChatPlateReader.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChatPlateReader.models.Document;
import com.ChatPlateReader.models.Message;


public interface DocumentRepository extends JpaRepository<Document, UUID> {

	List<Document> findByMessage(Message message);
	
}
