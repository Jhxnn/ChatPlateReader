package com.ChatPlateReader.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ChatPlateReader.models.enums.DocType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID documentId;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "message_id")
	private Message message;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="user_id")
	private User user;
	
	private DocType type;
	
	private boolean processed;
	
	
	
	

	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public DocType getType() {
		return type;
	}

	public void setType(DocType type) {
		this.type = type;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

}
