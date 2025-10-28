package com.api.barbershop.enums;

import lombok.Getter;

@Getter
public enum SchedulingStatus {

	PENDING("PENDING", "Pendente"), CONFIRMED("CONFIRMED", "Confirmado"), DONE("DONE", "Concluído"),
	CANCELED("CANCELED", "Cancelado");

	private final String name;
	private final String labelPtBr;

	SchedulingStatus(String name, String labelPtBr) {
		this.name = name;
		this.labelPtBr = labelPtBr;
	}

	public static SchedulingStatus fromName(String name) {
		for (SchedulingStatus status : values()) {
			if (status.name.equalsIgnoreCase(name)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown status: " + name);
	}
}