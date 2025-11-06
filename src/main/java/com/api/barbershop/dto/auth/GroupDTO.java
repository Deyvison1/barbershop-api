package com.api.barbershop.dto.auth;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GroupDTO {
	private UUID id;
	private String name;
	private String path;
}
