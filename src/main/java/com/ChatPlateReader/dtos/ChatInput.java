package com.ChatPlateReader.dtos;

import java.util.UUID;

public record ChatInput(String message, UUID userId) {

}
