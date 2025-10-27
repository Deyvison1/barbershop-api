package com.api.barbershop.services;

import com.api.barbershop.dtos.HaircutDTO;

import java.util.UUID;

public interface IHaircutService {
    HaircutDTO add(HaircutDTO dto);
    HaircutDTO update(UUID id, HaircutDTO dto);
    void remove(UUID id);
    HaircutDTO findByIdDTO(UUID id);
}
