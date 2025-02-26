package com.ChatPlateReader.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChatPlateReader.models.Message;
import com.ChatPlateReader.models.User;

public interface MessageRepository extends JpaRepository<Message, UUID> {

}
