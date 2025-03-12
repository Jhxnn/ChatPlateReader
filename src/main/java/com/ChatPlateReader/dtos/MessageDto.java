package com.ChatPlateReader.dtos;

import java.util.UUID;

import com.ChatPlateReader.models.enums.MsgType;
import org.springframework.web.multipart.MultipartFile;

public record MessageDto(UUID userId, String content, MultipartFile image, MsgType type) {


}
