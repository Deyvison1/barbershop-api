package com.api.barbershop.dto.specialty;

import java.io.Serial;

import com.api.barbershop.dto.barber.BarberDTO;
import com.api.barbershop.dto.base.BaseDTO;
import com.api.barbershop.validation.UniqueSpecialtyNameByBarber;

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
@UniqueSpecialtyNameByBarber
public class SpecialtyDTO extends BaseDTO {
	@Serial
	private static final long serialVersionUID = 1L;
	@Size(min = 3, max = 50, message = "Tamanho do nome deve ter entre 3 a 50 caracteres.")
	@NotBlank(message = "Nome é obrigatorio.")
	private String name;
	@Size(min = 5, max = 255, message = "Tamanho do nome deve ter entre 3 a 50 caracteres.")
	private String description;
	private BarberDTO barber;
}
