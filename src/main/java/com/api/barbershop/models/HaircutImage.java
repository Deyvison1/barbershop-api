package com.api.barbershop.models;

import com.api.barbershop.models.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "control_barbershop")
public class HaircutImage extends BaseEntity {

    private String filename;
    private String url; // path ou S3 URL
    private String contentType;


}
