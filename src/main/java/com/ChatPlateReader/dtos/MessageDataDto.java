package com.ChatPlateReader.dtos;

import com.ChatPlateReader.models.enums.MsgType;

import java.util.UUID;

public record MessageDataDto(String content, MsgType type) {
}
