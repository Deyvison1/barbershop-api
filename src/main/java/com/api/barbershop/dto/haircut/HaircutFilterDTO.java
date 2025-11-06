package com.api.barbershop.dto.haircut;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HaircutFilterDTO {
	private String name;
	private String description;
	private BigDecimal price;
	private Integer time;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime createdDate;
}
