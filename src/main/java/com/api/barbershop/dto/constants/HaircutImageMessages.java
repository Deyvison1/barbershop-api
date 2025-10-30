package com.api.barbershop.dto.constants;

public final class HaircutImageMessages {
	private HaircutImageMessages() {
	}

	// Swagger
	public static final String TITLE = "Haircuts Images.";
	public static final String DESCRIPTION = "Gerencia as imagens dos cortes de cabelos cadastrados..";

	public static final String BASE_API = "/api";
	public static final String BASE_PATH = BASE_API + "/haircut-image";

	// Roles
	public static final String ADMIN_AUTHORITY = "hasAuthority('ADMIN')";

	// Response descriptions
	public static final String CREATED = "Imagem do corte criado com sucesso.";
	public static final String DELETED = "Imagem do Corte excluído com sucesso.";
	public static final String UPLOAD = "Upload de Imagem realizado com sucesso.";
	public static final String DOWNLOAD = "Downoad de Imagem realizado com sucesso.";
	public static final String ACTIVE_IMAGE = "Imagem ativada com sucesso.";
}
