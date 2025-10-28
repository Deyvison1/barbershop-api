package com.api.barbershop.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.barbershop.dto.HaircutImageDTO;
import com.api.barbershop.exception.DefaultActiveImageException;
import com.api.barbershop.exception.NotFoundException;
import com.api.barbershop.mapper.IHaircutImageMapper;
import com.api.barbershop.model.Haircut;
import com.api.barbershop.model.HaircutImage;
import com.api.barbershop.repository.IHaircutImageRepository;
import com.api.barbershop.repository.IHaircutRepository;
import com.api.barbershop.service.IHaircutImageService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HaircutImageServiceImpl implements IHaircutImageService {

	private final IHaircutImageRepository repository;
	private final IHaircutImageMapper mapper;
	private final IHaircutRepository haircutRepository;

	@Override
	@Transactional
	@CacheEvict(value = "haircuts_images", key = "#id")
	public void remove(UUID id) {
		HaircutImage entity = findById(id);
		validateActiveImages(entity);
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public HaircutImageDTO uploadImageToDatabase(UUID haircutId, MultipartFile file, boolean active)
			throws IOException {
		Haircut haircut = haircutRepository.findById(haircutId)
				.orElseThrow(() -> new NotFoundException("Haircut not found: " + haircutId));

		if (active) {
			boolean existsActive = repository.existsByHaircutIdAndActiveTrue(haircutId);
			if (existsActive) {
				throw new DefaultActiveImageException("Já existe uma imagem ativa para este corte.");
			}
		}

		HaircutImage entity = HaircutImage.builder().haircut(haircut).filename(file.getOriginalFilename())
				.contentType(file.getContentType()).data(file.getBytes()).active(active).build();

		HaircutImage saved = repository.save(entity);

		return mapper.toDto(saved);
	}

	@Override
	public HaircutImageDTO getImageById(UUID id) {
		HaircutImage entity = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Imagem não encontrada: " + id));

		HaircutImageDTO dto = mapper.toDto(entity);

		return dto;
	}
	

	@Cacheable(value = "haircuts", key = "#id")
	private HaircutImage findById(UUID id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
	}

	private void validateActiveImages(HaircutImage entity) {
		List<HaircutImage> allImages = repository.findByHaircutId(entity.getHaircut().getId());

		if (allImages == null || allImages.isEmpty()) {
			return;
		}

		long activeCountExcludingCurrent = allImages.stream()
				.filter(img -> !img.getId().equals(entity.getId()) && Boolean.TRUE.equals(img.getActive())).count();

		if (activeCountExcludingCurrent == 0) {
			throw new DefaultActiveImageException("Não é possível remover a última imagem ativa.");
		}

		if (activeCountExcludingCurrent > 1) {
			throw new DefaultActiveImageException("Não pode haver mais de uma imagem ativa no corte.");
		}
	}

}
