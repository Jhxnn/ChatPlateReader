package com.ChatPlateReader.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ChatPlateReader.models.enums.StatusChat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID chatId;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "user1")
	private User user1;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "user2")
	private User user2;
	
	private StatusChat status;
	
	private LocalDateTime started;

	public UUID getChatId() {
		return chatId;
	}

	public void setChatId(UUID chatId) {
		this.chatId = chatId;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public StatusChat getStatus() {
		return status;
	}

	public void setStatus(StatusChat status) {
		this.status = status;
	}

	public LocalDateTime getStarted() {
		return started;
	}

	public void setStarted(LocalDateTime started) {
		this.started = started;
	}
	
	
	
	

}
