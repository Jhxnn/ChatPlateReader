package com.ChatPlateReader.dtos;

import java.util.UUID;

import com.ChatPlateReader.models.enums.MsgType;
import com.ChatPlateReader.models.enums.StatusChat;

public record ChatDto(String sender, String receiver, String content, MsgType type) {

}
