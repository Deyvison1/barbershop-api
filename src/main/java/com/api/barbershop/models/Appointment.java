package com.api.barbershop.models;

import com.api.barbershop.enums.SchedulingStatus;
import com.api.barbershop.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Appointment extends BaseEntity {

    private UUID barberId;
    private UUID customerId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Haircut haircut;

    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private SchedulingStatus status;
}
