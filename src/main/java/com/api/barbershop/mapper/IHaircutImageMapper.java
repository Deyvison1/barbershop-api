package com.api.barbershop.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.api.barbershop.dto.haircut.image.HaircutImageDTO;
import com.api.barbershop.mapper.base.IBaseMapper;
import com.api.barbershop.model.HaircutImage;

@Mapper(componentModel = "spring")
public interface IHaircutImageMapper extends IBaseMapper<HaircutImage, HaircutImageDTO> {
	
	@Override
	@Mapping(target = "haircut", ignore = true)
	HaircutImageDTO toDto(HaircutImage entity);

	@Override
	HaircutImage toEntity(HaircutImageDTO dto);

	@Mapping(target = "data", ignore = true)
	List<HaircutImageDTO> toDto(List<HaircutImage> entity);

	List<HaircutImage> toEntity(List<HaircutImageDTO> dto);
}
