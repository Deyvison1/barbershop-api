package com.api.barbershop.controller;

import com.api.barbershop.dto.HaircutDTO;
import com.api.barbershop.dto.HaircutFilterDTO;
import com.api.barbershop.dto.constants.HaircutMessages;
import com.api.barbershop.dto.haircut.HaircutCreateDTO;
import com.api.barbershop.service.IHaircutService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.data.domain.Pageable;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(HaircutMessages.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = HaircutMessages.TITLE, description = HaircutMessages.DESCRIPTION)
public class HaircutController {

	private final IHaircutService service;

	@GetMapping("/{id}")
	@PreAuthorize(HaircutMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "200", description = HaircutMessages.FIND_BY_ID)
	public ResponseEntity<HaircutDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findByIdDTO(id));
	}

	@GetMapping
	@PreAuthorize(HaircutMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "200", description = HaircutMessages.FIND_ALL)
	public ResponseEntity<Page<HaircutDTO>> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @ModelAttribute HaircutFilterDTO filter) {
		Pageable pageable = PageRequest.of(page, size);
		Page<HaircutDTO> haircuts = service.findAll(pageable, filter);
		return ResponseEntity.ok(haircuts);
	}

	@PostMapping
	@PreAuthorize(HaircutMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "201", description = HaircutMessages.CREATED)
	public ResponseEntity<HaircutDTO> add(@Valid @RequestBody final HaircutCreateDTO dto) {
		HaircutDTO response = service.add(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
				.toUri();
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	@PreAuthorize(HaircutMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "200", description = HaircutMessages.UPDATED)
	public ResponseEntity<HaircutDTO> update(@PathVariable UUID id, @Valid @RequestBody final HaircutDTO dto) {
		HaircutDTO response = service.update(id, dto);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(HaircutMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "204", description = HaircutMessages.DELETED)
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.remove(id);
		return ResponseEntity.noContent().build();
	}
}