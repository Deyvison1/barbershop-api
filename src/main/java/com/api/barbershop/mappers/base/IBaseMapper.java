package com.api.barbershop.mappers.base;

import java.util.List;

public interface IBaseMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);

    List<D> toDto(List<E> entities);
    List<E> toEntity(List<D> dtos);
}
