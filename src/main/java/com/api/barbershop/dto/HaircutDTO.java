package com.api.barbershop.dto;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;

import com.api.barbershop.dto.base.BaseDTO;
import com.api.barbershop.validation.HasActiveImage;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@HasActiveImage
public class HaircutDTO extends BaseDTO {
	@Serial
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Nome e obrigatorio.")
	@Size(min = 3, max = 50, message = "Tamanho do nome deve ter entre 3 a 50 caracteres.")
	@NotBlank(message = "Nome é obrigatorio.")
	private String name;
	@Size(min = 3, max = 255, message = "Tamanho da descrição deve ter entre 3 a 255 caracteres.")
    private String description;
    private BigDecimal price;
    @Valid
    private List<HaircutImageDTO> images;
}

