package com.ChatPlateReader.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ChatPlateReader.dtos.MessageDto;
import com.ChatPlateReader.models.Document;
import com.ChatPlateReader.models.Message;
import com.ChatPlateReader.models.User;
import com.ChatPlateReader.models.enums.MsgType;
import com.ChatPlateReader.repositories.DocumentRepository;
import com.ChatPlateReader.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	OcrService ocrService;
	

	public List<Message> findAll() {
		return messageRepository.findAll();
		
	}
	
	public Message findById(UUID id) {
		return messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot be found"));
	}
	
	public List<Message> findByChat(UUID chatId) {
		var chat  = chatService.findById(chatId);
		return messageRepository.findByChat(chat);
	}
	
	public List<String> ocrTest(MessageDto messageDto){
		if(messageDto.type() == MsgType.DOCUMENT) {
        	List<String> doc  = ocrService.returnTextDocument(messageDto.image());
        	if(doc != null) {
        		var document = new Document();
	        	document.setCnpj(doc.get(0));
	        	document.setCpf(doc.get(1));
	        	document.setData(doc.get(2));
	        	document.setProcessed(true);
	        	documentRepository.save(document);
	        	return doc;
        	}

        }
        
        if(messageDto.type() == MsgType.IMAGE) {
        	List<String> doc  = ocrService.returnTextLicensePlate(messageDto.image());
        	if(doc != null) {
        		var liceDocument = new Document();
	        	
	        	return null;
        	}
        }
		return null;
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
