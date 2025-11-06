package com.api.barbershop.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.barbershop.dto.barber.BarberDTO;
import com.api.barbershop.dto.barber.BarberFilterDTO;

public interface IBarberService {
	BarberDTO add(BarberDTO dto);

	BarberDTO findByIdDTO(UUID id);

	BarberDTO update(UUID id, BarberDTO dto);

	void remove(UUID id);

	Page<BarberDTO> findAll(Pageable pageable, BarberFilterDTO filter);
}
