package com.api.barbershop.service.client;

import com.api.barbershop.config.KeycloakFeignConfig;
import com.api.barbershop.dto.KeycloakUserDTO;
import com.api.barbershop.dto.auth.GroupDTO;
import com.api.barbershop.dto.auth.KeycloakApiPaths;
import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "keycloakAdmin", url = "${keycloak-config.internal-url}", configuration = KeycloakFeignConfig.class)
public interface IKeycloakAdminFeignClient {
	// =========== GROUPS ==============
	@GetMapping(KeycloakApiPaths.Groups.MEMBERS)
	List<KeycloakUserDTO> getUserByGroupId(@PathVariable("groupId") UUID groupId);

	@GetMapping(KeycloakApiPaths.Groups.SEARCH)
	List<GroupDTO> getGroupByName(@PathVariable("groupName") String groupName);
}
