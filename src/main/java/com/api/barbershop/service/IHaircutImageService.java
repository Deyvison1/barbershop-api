package com.api.barbershop.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.api.barbershop.dto.HaircutImageDTO;

public interface IHaircutImageService {
	void remove(UUID id);
	HaircutImageDTO uploadImageToDatabase(UUID haircutId, MultipartFile file, boolean active) throws IOException;
	HaircutImageDTO getImageById(@PathVariable UUID id);
}
