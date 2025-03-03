package com.ChatPlateReader.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ChatPlateReader.models.Chat;
import com.ChatPlateReader.models.User;


public interface ChatRepository extends JpaRepository<Chat, UUID> {

	
	@Query("SELECT c FROM Chat c WHERE c.user1 = :user OR c.user2 = :user")
	List<Chat> findByUser(@Param("user") User user);
	
}
