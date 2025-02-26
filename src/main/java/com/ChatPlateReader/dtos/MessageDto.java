package com.ChatPlateReader.dtos;

import java.util.UUID;

import com.ChatPlateReader.models.enums.MsgType;

public record MessageDto(UUID chatId, UUID userId, String content, MsgType type) {

}
