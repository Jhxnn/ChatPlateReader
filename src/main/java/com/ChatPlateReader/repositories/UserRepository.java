package com.ChatPlateReader.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChatPlateReader.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
