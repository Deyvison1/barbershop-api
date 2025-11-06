package com.api.barbershop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.barbershop.dto.KeycloakUserDTO;
import com.api.barbershop.dto.auth.GroupDTO;import com.api.barbershop.exception.NotFoundException;
import com.api.barbershop.service.IKeycloakService;
import com.api.barbershop.service.client.IKeycloakAdminFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements IKeycloakService {
	private final IKeycloakAdminFeignClient kcClient;

	@Override
	public List<KeycloakUserDTO> getUserByGroupId(String name) {
		Optional<GroupDTO> groupsByName = kcClient.getGroupByName(name).stream().findFirst();
		if(groupsByName.isPresent()) {
			return kcClient.getUserByGroupId(groupsByName.get().getId());
		}
		throw new NotFoundException(name);
	}
}
