package com.api.barbershop.service;

import java.util.List;

import com.api.barbershop.dto.KeycloakUserDTO;

public interface IKeycloakService {
	List<KeycloakUserDTO> getUserByGroupId(String name);
}
