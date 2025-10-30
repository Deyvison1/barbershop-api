package com.api.barbershop.service.impl;

import com.api.barbershop.dto.HaircutDTO;
import com.api.barbershop.dto.HaircutFilterDTO;
import com.api.barbershop.dto.haircut.HaircutCreateDTO;
import com.api.barbershop.exception.NotChangedException;
import com.api.barbershop.exception.NotFoundException;
import com.api.barbershop.mapper.IHaircutMapper;
import com.api.barbershop.model.Haircut;
import com.api.barbershop.model.HaircutImage;
import com.api.barbershop.repository.IHaircutRepository;
import com.api.barbershop.repository.specification.HaircutSpecification;
import com.api.barbershop.service.IHaircutService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HaircutServiceImpl implements IHaircutService {

	private final IHaircutMapper mapper;
	private final IHaircutRepository repository;

	@Override
	@Transactional
	@Cacheable(value = "haircuts")
	public Page<HaircutDTO> findAll(Pageable pageable, HaircutFilterDTO filter) {
		Specification<Haircut> spec = HaircutSpecification.filterBy(filter);

		Pageable pageableRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(Sort.Direction.DESC, "createdDate"));

		Page<Haircut> haircuts = repository.findAll(spec, pageableRequest);

		haircuts.forEach(
				h -> h.setImages(h.getImages().stream().filter(HaircutImage::getActive).collect(Collectors.toList())));

		return haircuts.map(mapper::toDto);
	}

	@Override
	@Transactional
	@CacheEvict(value = "haircuts", allEntries = true)
	public HaircutDTO add(HaircutCreateDTO dto) {
		Haircut entity = mapper.toEntityCreate(dto);
		return mapper.toDto(repository.save(entity));
	}

	@Override
	@Transactional
	@CacheEvict(value = "haircuts", allEntries = true)
	public HaircutDTO update(UUID id, HaircutDTO dto) {
		Haircut entity = findById(id);
		setEntityToUpdate(entity, dto);
		return mapper.toDto(repository.save(entity));
	}

	@Override
	@Transactional
	@CacheEvict(value = "haircuts", key = "#id")
	public void remove(UUID id) {
		findById(id);
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public HaircutDTO findByIdDTO(UUID id) {
		Haircut entity = findById(id);
		if (entity.getImages().isEmpty()) {
		}
		return mapper.toDto(entity);
	}

	private void setEntityToUpdate(Haircut entity, HaircutDTO dto) {
		if (isChange(entity, dto)) {
			if (Objects.nonNull(dto.getName()))
				entity.setName(dto.getName());
			if (Objects.nonNull(dto.getDescription()))
				entity.setDescription(dto.getDescription());
			if (Objects.nonNull(dto.getPrice()))
				entity.setPrice(dto.getPrice());
			if (Objects.nonNull(dto.getTime()))
				entity.setTime(dto.getTime());
		}
	}

	private boolean isChange(Haircut entity, HaircutDTO dto) {
		if (Objects.nonNull(entity) && Objects.nonNull(dto)) {

			return dto.getName() != entity.getName() || dto.getDescription() != entity.getDescription()
					|| dto.getPrice() != entity.getPrice() || dto.getTime() != entity.getTime();
		}
		throw new NotChangedException();
	}

	private Haircut findById(UUID id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
	}
}
