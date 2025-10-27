package com.api.barbershop.mappers.base;

import com.api.barbershop.dtos.HaircutDTO;
import com.api.barbershop.models.Haircut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IHaircutMapper extends IBaseMapper<Haircut, HaircutDTO> {
}
