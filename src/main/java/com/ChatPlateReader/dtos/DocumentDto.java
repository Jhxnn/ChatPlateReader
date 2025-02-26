package com.ChatPlateReader.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ChatPlateReader.models.enums.DocType;

public record DocumentDto(UUID messageId, UUID userId, DocType type, boolean processed, LocalDateTime sendTime) {

}
