package com.ChatPlateReader.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ChatPlateReader.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	UserDetails findByEmail(String email);
	User findByEmail2(String email);

}
