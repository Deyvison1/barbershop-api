package com.api.barbershop.services.impl;

import com.api.barbershop.dtos.HaircutDTO;
import com.api.barbershop.exceptions.NotChangedException;
import com.api.barbershop.exceptions.NotFoundException;
import com.api.barbershop.mappers.base.IHaircutMapper;
import com.api.barbershop.models.Haircut;
import com.api.barbershop.repositories.IHaircutRepository;
import com.api.barbershop.services.IHaircutService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HaircutServiceImpl implements IHaircutService {
    private final IHaircutMapper mapper;
    private final IHaircutRepository repository;

    @Override
    @CacheEvict(value = "haircuts", allEntries = true)
    public HaircutDTO add(HaircutDTO dto) {
        Haircut entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @CacheEvict(value = "haircuts", allEntries = true)
    public HaircutDTO update(UUID id, HaircutDTO dto) {
        Haircut entity = findById(id);
        setEntityToUpdate(entity, dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @CacheEvict(value = "haircuts", key = "#id")
    public void remove(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "haircuts", key = "#id")
    public HaircutDTO findByIdDTO(UUID id) {
        Haircut entity = findById(id);
        return mapper.toDto(entity);
    }

    private void setEntityToUpdate(Haircut entity, HaircutDTO dto) {
        if(isChange(entity, dto)) {
            if(dto.getName() != null)
                entity.setName(dto.getName());
            if(dto.getDescription() != null)
                entity.setDescription(dto.getDescription());
        }
    }

    private boolean isChange(Haircut entity, HaircutDTO dto) {
        if(Objects.nonNull(entity) && Objects.nonNull(dto)) {
            return !dto.getName().equals(entity.getName()) && !dto.getDescription().equals(entity.getDescription());
        }
        throw new NotChangedException();
    }

    private Haircut findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
    }
}
