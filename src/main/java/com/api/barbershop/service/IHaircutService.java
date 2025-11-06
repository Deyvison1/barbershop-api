package com.api.barbershop.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.barbershop.dto.haircut.HaircutCreateDTO;
import com.api.barbershop.dto.haircut.HaircutDTO;
import com.api.barbershop.dto.haircut.HaircutFilterDTO;

public interface IHaircutService {
	HaircutDTO add(HaircutCreateDTO dto);

	HaircutDTO update(UUID id, HaircutDTO dto);

	void remove(UUID id);

	HaircutDTO findByIdDTO(UUID id);

	Page<HaircutDTO> findAll(Pageable pageable, HaircutFilterDTO filter);
}
