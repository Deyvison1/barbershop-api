package com.api.barbershop.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.api.barbershop.dto.haircut.image.HaircutImageDTO;

public interface IHaircutImageService {
	void remove(UUID id);
	HaircutImageDTO uploadImageToDatabase(UUID haircutId, MultipartFile file, boolean active) throws IOException;
	HaircutImageDTO getImageById( UUID id);
	void activeImage(UUID id);
}
