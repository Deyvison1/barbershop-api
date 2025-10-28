package com.api.barbershop.dto.constants;

public final class HaircutMessages {

	private HaircutMessages() {
	}
	// Swagger
	public static final String TITLE = "Haircuts.";
	public static final String DESCRIPTION = "Gerencia os cortes de cabelo disponíveis.";

	public static final String BASE_API = "/api";
	public static final String BASE_PATH = BASE_API + "/haircut";
	
	// Roles
	public static final String ADMIN_AUTHORITY = "hasAuthority('ADMIN')";

	// Response descriptions
	public static final String FIND_BY_ID = "Busca dos do registro pelo identificador realizada com sucesso.";
	public static final String FIND_ALL = "Busca dos registros com paginação realizada com sucesso.";
	public static final String CREATED = "Corte criado com sucesso.";
	public static final String UPDATED = "Corte atualizado com sucesso.";
	public static final String DELETED = "Corte excluído com sucesso.";
	
}
