package com.api.barbershop.dto;

import com.api.barbershop.dto.base.BaseDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class HaircutImageDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "O nome do arquivo é obrigatório.")
	private String filename;
	@NotBlank(message = "O tipo de conteúdo da imagem é obrigatório.")
	private String contentType;
	private HaircutDTO haircut;
	private byte[] data;
	@NotNull(message = "É preciso informar se a imagem está ativa ou não.")
	private Boolean active;
}
