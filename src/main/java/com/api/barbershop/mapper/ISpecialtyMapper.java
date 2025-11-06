package com.api.barbershop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.api.barbershop.dto.specialty.SpecialtyDTO;
import com.api.barbershop.mapper.base.IBaseMapper;
import com.api.barbershop.model.Specialty;

@Mapper(componentModel = "spring")
public interface ISpecialtyMapper extends IBaseMapper<Specialty, SpecialtyDTO> {

	@Override
	@Mapping(target = "barber", ignore = true)
	SpecialtyDTO toDto(Specialty entity);

	@Override
	Specialty toEntity(SpecialtyDTO dto);
}
