package com.ChatPlateReader.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ChatPlateReader.dtos.ChatDto;
import com.ChatPlateReader.models.Chat;
import com.ChatPlateReader.repositories.ChatRepository;

@Service
public class ChatService {

	
	@Autowired
	ChatRepository chatRepository;
	

	
	public List<Chat> findAll() {
		return chatRepository.findAll();
	}
	
	public Chat findById(UUID id) {
		return chatRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
	}
	
	public Chat createChat(ChatDto chatDto) {
		var chat = new Chat();
		BeanUtils.copyProperties(chatDto, chat);
		return chatRepository.save(chat);
	}
	
	public void deleteChat(UUID id) {
		var chat = findById(id);
		chatRepository.delete(chat);
	}
}
