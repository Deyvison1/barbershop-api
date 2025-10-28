package com.api.barbershop.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.barbershop.dto.HaircutDTO;
import com.api.barbershop.dto.HaircutFilterDTO;

public interface IHaircutService {
	HaircutDTO add(HaircutDTO dto);

	HaircutDTO update(UUID id, HaircutDTO dto);

	void remove(UUID id);

	HaircutDTO findByIdDTO(UUID id);

	Page<HaircutDTO> findAll(Pageable pageable, HaircutFilterDTO filter);
}
