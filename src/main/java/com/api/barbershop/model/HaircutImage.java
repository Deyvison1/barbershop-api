package com.api.barbershop.model;

import java.io.Serial;

import com.api.barbershop.model.base.BaseEntity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(schema = "control_barbershop")
public class HaircutImage extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;
	private String filename;
	private String contentType;
	@Column(nullable = false)
	private Boolean active;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] data;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "haircut_id", nullable = false)
	private Haircut haircut;

}
