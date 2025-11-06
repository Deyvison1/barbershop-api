package com.api.barbershop.dto.constants;

public class KeycloakMessages {

	private KeycloakMessages() {
	}

	// Swagger
	public static final String TITLE = "Keycloak.";
	public static final String DESCRIPTION = "Gerencia as chamadas para o keycloak..";

	public static final String BASE_API = "/api";
	public static final String BASE_PATH = BASE_API + "/keycloak";

	// Roles
	public static final String ADMIN_AUTHORITY = "hasAuthority('ADMIN')";

	// Response descriptions
	public static final String FIND_ALL_USERS_BY_GROUP = "Imagem ativada com sucesso.";
}
