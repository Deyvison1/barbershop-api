package com.api.barbershop.model;

import com.api.barbershop.enums.SchedulingStatus;
import com.api.barbershop.model.base.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
public class Appointment extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private UUID barberId;
    private UUID customerId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Haircut haircut;

    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private SchedulingStatus status;
}
