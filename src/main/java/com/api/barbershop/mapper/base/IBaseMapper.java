package com.api.barbershop.mapper.base;

import com.api.barbershop.dto.base.BaseDTO;
import com.api.barbershop.model.base.BaseEntity;

public interface IBaseMapper<E extends BaseEntity, D extends BaseDTO> {
    D toDto(E entity);

    E toEntity(D dto);
}