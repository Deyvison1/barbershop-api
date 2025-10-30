package com.api.barbershop.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.barbershop.dto.MenuDTO;
import com.api.barbershop.dto.constants.MenuMessages;
import com.api.barbershop.enums.MenuEnum;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(MenuMessages.BASE_PATH)
@Tag(name = MenuMessages.TITLE, description = MenuMessages.DESCRIPTION)
public class MenuController {

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	@ApiResponse(responseCode = "201", description = MenuMessages.FIND_ALL)
	public ResponseEntity<Set<MenuDTO>> finAll() {
		return ResponseEntity.ok(MenuEnum.findAll());
	}
}