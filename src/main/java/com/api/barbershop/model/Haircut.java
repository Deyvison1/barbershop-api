package com.api.barbershop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

import com.api.barbershop.model.base.BaseEntity;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "control_barbershop")
public class Haircut extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(length = 50, nullable = false)
	@Size(min = 3, max = 50, message = "Tamanho do nome deve ter entre 3 a 50 caracteres.")
	@NotBlank(message = "Nome é obrigatorio.")
	private String name;
	@Size(min = 3, max = 255, message = "Tamanho da descrição deve ter entre 3 a 255 caracteres.")
	private String description;
	private BigDecimal price;
	@OneToMany(mappedBy = "haircut", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HaircutImage> images;
}