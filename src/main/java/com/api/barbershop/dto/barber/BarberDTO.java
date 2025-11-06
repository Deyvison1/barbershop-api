package com.api.barbershop.dto.barber;

import java.io.Serial;
import java.util.List;

import com.api.barbershop.dto.base.BaseDTO;
import com.api.barbershop.dto.specialty.SpecialtyDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BarberDTO extends BaseDTO {
	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Usuario e obrigatorio.")
	private String userId;
	@Size(min = 3, max = 50, message = "Tamanho do nome deve ter entre 3 a 50 caracteres.")
	@NotBlank(message = "Nome é obrigatorio.")
	private String name;
	private List<SpecialtyDTO> specialties;
}
