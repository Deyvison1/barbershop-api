package com.api.barbershop.dto.constants;

public class BarberMessages {

	private BarberMessages() {
	}
	// Swagger
	public static final String TITLE = "Barbers.";
	public static final String DESCRIPTION = "Gerencia os barbeiros.";

	public static final String BASE_API = "/api";
	public static final String BASE_PATH = BASE_API + "/barber";
	
	// Roles
	public static final String ADMIN_AUTHORITY = "hasAuthority('ADMIN')";

	// Response descriptions
	public static final String FIND_BY_ID = "Busca dos do registro pelo identificador realizada com sucesso.";
	public static final String FIND_ALL = "Busca dos registros com paginação realizada com sucesso.";
	public static final String CREATED = "Barbeiro criado com sucesso.";
	public static final String UPDATED = "Barbeiro atualizado com sucesso.";
	public static final String DELETED = "Barbeiro excluído com sucesso.";
}
