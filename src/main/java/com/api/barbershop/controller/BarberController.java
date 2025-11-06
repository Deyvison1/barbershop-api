package com.api.barbershop.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.barbershop.dto.barber.BarberDTO;
import com.api.barbershop.dto.barber.BarberFilterDTO;
import com.api.barbershop.dto.constants.BarberMessages;
import com.api.barbershop.service.IBarberService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(BarberMessages.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = BarberMessages.TITLE, description = BarberMessages.DESCRIPTION)
public class BarberController {
	private final IBarberService service;

	@GetMapping("/{id}")
	@PreAuthorize(BarberMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "200", description = BarberMessages.FIND_BY_ID)
	public ResponseEntity<BarberDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findByIdDTO(id));
	}

	@GetMapping
	@PreAuthorize(BarberMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "200", description = BarberMessages.FIND_ALL)
	public ResponseEntity<Page<BarberDTO>> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @ModelAttribute BarberFilterDTO filter) {
		Pageable pageable = PageRequest.of(page, size);
		Page<BarberDTO> barbers = service.findAll(pageable, filter);
		return ResponseEntity.ok(barbers);
	}

	@PostMapping
	@PreAuthorize(BarberMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "201", description = BarberMessages.CREATED)
	public ResponseEntity<BarberDTO> add(@Valid @RequestBody final BarberDTO dto) {
		BarberDTO response = service.add(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
				.toUri();
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	@PreAuthorize(BarberMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "200", description = BarberMessages.UPDATED)
	public ResponseEntity<BarberDTO> update(@PathVariable UUID id, @Valid @RequestBody final BarberDTO dto) {
		BarberDTO response = service.update(id, dto);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(BarberMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "204", description = BarberMessages.DELETED)
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.remove(id);
		return ResponseEntity.noContent().build();
	}
}
