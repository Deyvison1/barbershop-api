package com.api.barbershop.models;

import com.api.barbershop.models.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Haircut extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}