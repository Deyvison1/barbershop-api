package com.api.barbershop.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.api.barbershop.dto.barber.BarberDTO;
import com.api.barbershop.mapper.base.IBaseMapper;
import com.api.barbershop.model.Barber;

@Mapper(componentModel = "spring", uses = { ISpecialtyMapper.class })
public interface IBarberMapper extends IBaseMapper<Barber, BarberDTO> {


	@AfterMapping
	default void linkImages(@MappingTarget Barber barber, BarberDTO dto) {
		if (dto.getSpecialties() != null && barber.getSpecialties() != null) {
			barber.getSpecialties().forEach(s -> s.setBarber(barber));
		}
	}
}
