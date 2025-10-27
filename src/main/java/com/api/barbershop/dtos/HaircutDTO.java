package com.api.barbershop.dtos;

import com.api.barbershop.dtos.base.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HaircutDTO extends BaseDTO {
    private String name;
    private String description;
}
