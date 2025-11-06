package com.api.barbershop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.barbershop.dto.KeycloakUserDTO;
import com.api.barbershop.dto.constants.HaircutMessages;
import com.api.barbershop.dto.constants.KeycloakMessages;
import com.api.barbershop.service.IKeycloakService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(KeycloakMessages.BASE_PATH)
@RequiredArgsConstructor
@Tag(name = KeycloakMessages.TITLE, description = KeycloakMessages.DESCRIPTION)
public class KeycloakController {

	private final IKeycloakService service;

	@GetMapping("/{groupName}")
	@PreAuthorize(HaircutMessages.ADMIN_AUTHORITY)
	@ApiResponse(responseCode = "200", description = KeycloakMessages.FIND_ALL_USERS_BY_GROUP)
	public ResponseEntity<List<KeycloakUserDTO>> getUserByGroupId(@PathVariable String groupName) {
		return ResponseEntity.ok(service.getUserByGroupId(groupName));
	}
}
