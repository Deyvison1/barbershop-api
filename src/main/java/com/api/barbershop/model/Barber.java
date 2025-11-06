package com.api.barbershop.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import com.api.barbershop.model.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(schema = "control_barbershop")
public class Barber extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Usuario e obrigatorio.")
	private String userId;
	@Size(min = 3, max = 50, message = "Tamanho do nome deve ter entre 3 a 50 caracteres.")
	@NotBlank(message = "Nome é obrigatorio.")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "barber_id")
	@Builder.Default
	private List<Specialty> specialties = new ArrayList<Specialty>();
}
