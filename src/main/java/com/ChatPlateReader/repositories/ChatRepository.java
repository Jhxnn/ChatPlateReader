package com.ChatPlateReader.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChatPlateReader.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, UUID> {

}
