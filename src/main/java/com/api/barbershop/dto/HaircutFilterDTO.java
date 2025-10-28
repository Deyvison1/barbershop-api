package com.api.barbershop.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HaircutFilterDTO {
	private String name;
	private String description;
	private BigDecimal price;
}
