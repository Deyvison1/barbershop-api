package com.api.barbershop.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KeycloakUserDTO {
	private UUID id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
}
