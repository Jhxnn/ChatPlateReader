package com.ChatPlateReader.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ChatPlateReader.dtos.MessageDto;
import com.ChatPlateReader.models.Message;
import com.ChatPlateReader.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ChatService chatService;
	

	public List<Message> findAll() {
		return messageRepository.findAll();
		
	}
	
	public Message findById(UUID id) {
		return messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
	}
	
	public Message findByChat(UUID chatId) {
		var chat  = chatService.findById(chatId);
		return messageRepository.findByChat(chat);
	}

	public Message createMessage(MessageDto messageDto) {
		var message = new Message();
		BeanUtils.copyProperties(messageDto, message);
		return messageRepository.save(message);
	}

	public void deleteMessage(UUID id) {
		var message = findById(id);
		messageRepository.delete(message);
	}
}
