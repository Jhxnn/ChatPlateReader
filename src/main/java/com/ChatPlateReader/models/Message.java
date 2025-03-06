package com.ChatPlateReader.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ChatPlateReader.models.enums.MsgType;

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
@Table(name = "messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID messageId;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "chat_id")
	private Chat chat;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="user_id")
	private User user;
	
	private String content;
	
	private MsgType type;
	
	private LocalDateTime sendTime;
	
	@PrePersist
    protected void onCreate() {
        sendTime = LocalDateTime.now();
    }
	
	

	public UUID getMessageId() {
		return messageId;
	}

	public void setMessageId(UUID messageId) {
		this.messageId = messageId;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}

	public LocalDateTime getSendTime() {
		return sendTime;
	}

	public void setSendTime(LocalDateTime sendTime) {
		this.sendTime = sendTime;
	}



	
	
	

}
