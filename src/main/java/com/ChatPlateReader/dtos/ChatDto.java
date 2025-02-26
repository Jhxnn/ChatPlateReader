package com.ChatPlateReader.dtos;

import java.util.UUID;

import com.ChatPlateReader.models.enums.StatusChat;

public record ChatDto(UUID user1, UUID user2, StatusChat status) {

}
