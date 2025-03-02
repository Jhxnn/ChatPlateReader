package com.ChatPlateReader.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChatPlateReader.models.LicensePlate;
import com.ChatPlateReader.models.Message;

public interface LicensePlateRepository extends JpaRepository<LicensePlate, UUID> {

	List<LicensePlate> findByMessage(Message message);
}
