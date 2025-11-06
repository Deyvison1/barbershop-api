package com.api.barbershop.model;

import java.io.Serial;

import com.api.barbershop.model.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "control_barbershop", name = "specialty", uniqueConstraints = @UniqueConstraint(columnNames = { "name",
		"barber_id" }))
public class Specialty extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;
	@Size(min = 3, max = 50, message = "Tamanho do nome deve ter entre 3 a 50 caracteres.")
	@NotBlank(message = "Nome é obrigatorio.")
	private String name;
	@Size(min = 5, max = 255, message = "Tamanho do nome deve ter entre 3 a 50 caracteres.")
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "barber_id", nullable = false)
	private Barber barber;
}
