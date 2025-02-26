package com.ChatPlateReader.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChatPlateReader.models.Document;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

}
