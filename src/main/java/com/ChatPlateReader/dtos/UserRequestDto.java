package com.ChatPlateReader.dtos;

import com.ChatPlateReader.models.enums.UserRole;

public record UserRequestDto(String name, String email, String password, UserRole role) {

}
