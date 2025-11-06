package com.api.barbershop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.api.barbershop.dto.barber.BarberDTO;
import com.api.barbershop.dto.barber.BarberFilterDTO;
import com.api.barbershop.dto.specialty.SpecialtyDTO;
import com.api.barbershop.exception.NotChangedException;
import com.api.barbershop.exception.NotFoundException;
import com.api.barbershop.mapper.IBarberMapper;
import com.api.barbershop.model.Barber;
import com.api.barbershop.model.Specialty;
import com.api.barbershop.repository.IBarberRepository;
import com.api.barbershop.repository.specification.BarberSpecification;
import com.api.barbershop.service.IBarberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarberServiceImpl implements IBarberService {

	private final IBarberRepository repository;
	private final IBarberMapper mapper;

	@Override
	public BarberDTO add(BarberDTO dto) {
		Barber entity = mapper.toEntity(dto);
		Barber saved = repository.save(entity);
		return mapper.toDto(saved);
	}

	@Override
	public BarberDTO findByIdDTO(UUID id) {
		Barber entity = findById(id);
		return mapper.toDto(entity);
	}

	@Override
	public BarberDTO update(UUID id, BarberDTO dto) {
		Barber entity = findById(id);
		setEntityToUpdate(entity, dto);
		return mapper.toDto(repository.save(entity));
	}

	@Override
	public void remove(UUID id) {
		findById(id);
		repository.deleteById(id);
	}

	@Override
	@Transactional
	@Cacheable(value = "barbers")
	public Page<BarberDTO> findAll(Pageable pageable, BarberFilterDTO filter) {
		Specification<Barber> spec = BarberSpecification.filterBy(filter);

		Pageable pageableRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(Sort.Direction.DESC, "createdDate"));

		Page<Barber> haircuts = repository.findAll(spec, pageableRequest);

		return haircuts.map(mapper::toDto);
	}

	private Barber findById(UUID id) {
		Barber entity = repository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
		return entity;
	}

	private void setEntityToUpdate(Barber entity, BarberDTO dto) {
		if (isChange(entity, dto)) {
			if (Objects.nonNull(dto.getName()))
				entity.setName(dto.getName());
			if (Objects.nonNull(dto.getUserId()))
				entity.setUserId(dto.getUserId());
		}
		setEntitySpecialtiesToUpdate(entity.getSpecialties(), dto.getSpecialties());
	}

	private void setEntitySpecialtiesToUpdate(List<Specialty> entities, List<SpecialtyDTO> dtos) {
	    Map<UUID, Specialty> existingById = entities.stream()
	            .filter(e -> e.getId() != null)
	            .collect(Collectors.toMap(Specialty::getId, e -> e));

	    List<Specialty> updatedEntities = dtos.stream()
	            .map(dto -> {
	                Specialty entity = Optional.ofNullable(dto.getId())
	                        .map(existingById::get)
	                        .orElseGet(Specialty::new);

	                if (isChangeSpecialty(entity, dto)) {
	                    entity.setName(dto.getName());
	                    entity.setDescription(dto.getDescription());
	                }

	                return entity;
	            })
	            .toList();

	    entities.clear();
	    entities.addAll(updatedEntities);
	}

	private boolean isChangeSpecialty(Specialty entity, SpecialtyDTO dto) {
	    if (entity == null || dto == null) {
	        return false;
	    }

	    return !Objects.equals(entity.getName(), dto.getName())
	            || !Objects.equals(entity.getDescription(), dto.getDescription());
	}


	private boolean isChange(Barber entity, BarberDTO dto) {
		if (Objects.nonNull(entity) && Objects.nonNull(dto)) {

			return dto.getName() != entity.getName() || dto.getUserId() != entity.getUserId();
		}
		throw new NotChangedException();
	}

}
