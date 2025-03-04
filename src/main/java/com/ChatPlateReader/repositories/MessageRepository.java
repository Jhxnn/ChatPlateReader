package com.ChatPlateReader.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChatPlateReader.models.Chat;
import com.ChatPlateReader.models.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {

	List<Message> findByChat(Chat chat);
}
