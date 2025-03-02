package com.ChatPlateReader.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChatPlateReader.models.Chat;
import com.ChatPlateReader.models.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {

	Message findByChat(Chat chat);
}
