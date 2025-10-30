package com.api.barbershop.mapper;

import com.api.barbershop.dto.HaircutDTO;
import com.api.barbershop.dto.haircut.HaircutCreateDTO;
import com.api.barbershop.mapper.base.IBaseMapper;
import com.api.barbershop.model.Haircut;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = { IHaircutImageMapper.class })
public interface IHaircutMapper extends IBaseMapper<Haircut, HaircutDTO> {

	@Override
	HaircutDTO toDto(Haircut entity);

	@Override
	Haircut toEntity(HaircutDTO dto);

	List<HaircutDTO> toDto(List<Haircut> entity);

	List<Haircut> toEntity(List<HaircutDTO> dto);
	
    @Mapping(target = "images", ignore = true)
	Haircut toEntityCreate(HaircutCreateDTO dto);

	@AfterMapping
	default void linkImages(@MappingTarget Haircut haircut, HaircutDTO dto) {
		if (dto.getImages() != null && haircut.getImages() != null) {
			haircut.getImages().forEach(img -> img.setHaircut(haircut));
		}
	}
}
