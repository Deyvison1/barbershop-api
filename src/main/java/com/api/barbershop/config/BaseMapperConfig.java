package com.api.barbershop.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingInheritanceStrategy;

@MapperConfig(
	    componentModel = "spring",
	    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG
	)
public interface BaseMapperConfig {

}
