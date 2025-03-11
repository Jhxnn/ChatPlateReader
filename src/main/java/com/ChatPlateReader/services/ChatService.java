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
import com.ChatPlateReader.models.Document;
import com.ChatPlateReader.models.Message;
import com.ChatPlateReader.models.User;
import com.ChatPlateReader.models.enums.MsgType;
import com.ChatPlateReader.models.enums.StatusChat;
import com.ChatPlateReader.repositories.ChatRepository;
import com.ChatPlateReader.repositories.DocumentRepository;
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
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	OcrService ocrService;

	
	public List<Chat> findAll() {
		return chatRepository.findAll();
	}
	
	public Chat findById(UUID id) {
		return chatRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
	}
	
	
//
//	 public void sendMessage(@Payload ChatDto chatDto) {
//	        messagingTemplate.convertAndSendToUser(
//	           chatDto.receiver(), "/queue/messages", chatDto
//	        );
//	        var sender = userRepository.findByEmail(chatDto.sender());
//	        var receiver = userRepository.findByEmail(chatDto.receiver());
//	        var chat = chatRepository.findByUsers((User) sender,(User) receiver);
//	        if(chat == null) {
//	        	 Chat newChat = new Chat();
//                newChat.setUser1((User) sender);
//                newChat.setUser2((User) receiver);
//                newChat.setStatus(StatusChat.OPEN);
//                chatRepository.save(newChat);
//	        }
//
//	        var message = new Message();
//	        message.setContent(chatDto.content());
//	        message.setUser((User) sender);
//	        message.setType(chatDto.type());
//	        message.setChat(chat);
//
//	        messageRepository.save(message);
//
//	        if(chatDto.type() == MsgType.DOCUMENT) {
//	        	List<String> doc  = ocrService.returnTextDocument(chatDto.image());
//	        	if(doc != null) {
//	        		var document = new Document();
//		        	document.setCnpj(doc.get(0));
//		        	document.setCpf(doc.get(1));
//		        	document.setData(doc.get(2));
//		        	document.setMessage(message);
//		        	document.setUser((User) sender);
//		        	document.setProcessed(true);
//		        	documentRepository.save(document);
//
//	        	}
//	        }
//
//	        if(chatDto.type() == MsgType.IMAGE) {
//	        	List<String> doc  = ocrService.returnTextLicensePlate(chatDto.content());
//	        	if(doc != null) {
//	        		var liceDocument = new Document();
//
//
//	        	}
//	        }
//
//	    }
	
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
