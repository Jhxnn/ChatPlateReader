package com.ChatPlateReader.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ChatPlateReader.dtos.ChatDto;
import com.ChatPlateReader.models.Chat;
import com.ChatPlateReader.models.Message;
import com.ChatPlateReader.models.enums.StatusChat;
import com.ChatPlateReader.repositories.ChatRepository;
import com.ChatPlateReader.repositories.MessageRepository;
import com.ChatPlateReader.repositories.UserRepository;

@Service
public class ChatService {

	
	@Autowired
	ChatRepository chatRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

	
	public List<Chat> findAll() {
		return chatRepository.findAll();
	}
	
	public Chat findById(UUID id) {
		return chatRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
	}
	
	 public void sendMessage(@Payload ChatDto chatDto) {
	        messagingTemplate.convertAndSendToUser(
	           chatDto.receiver(), "/queue/messages", chatDto
	        );
	        var message = new Message();
	        message.setContent(chatDto.content());
	        var sender = userRepository.findByEmail2(chatDto.sender());
	        var receiver = userRepository.findByEmail2(chatDto.receiver());
	        message.setUser(sender);
	        message.setType(chatDto.type());
	        
	        var chat =  new Chat();
	        chat.setUser1(sender);
	        chat.setUser2(receiver);
	        chat.setStatus(StatusChat.OPEN);
	        chatRepository.save(chat);
	        message.setChat(chat);
	        
	        messageRepository.save(message);
	        
	        
	    }
	
	public Chat createChat(ChatDto chatDto) {
		var chat = new Chat();
		BeanUtils.copyProperties(chatDto, chat);
		return chatRepository.save(chat);
	}
	
	public List<Chat> findByUser(UUID id) {
		var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
		return chatRepository.findByUser(user);
	}
	
	public void deleteChat(UUID id) {
		var chat = findById(id);
		chatRepository.delete(chat);
	}
}
