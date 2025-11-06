package com.api.barbershop.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.barbershop.dto.specialty.SpecialtyDTO;
import com.api.barbershop.repository.ISpecialtyRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueSpecialtyNameByBarberValidator implements ConstraintValidator<UniqueSpecialtyNameByBarber, SpecialtyDTO> {

    @Autowired
    private ISpecialtyRepository repository;

    @Override
    public boolean isValid(SpecialtyDTO dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getName() == null || dto.getBarber() == null || dto.getBarber().getId() == null) {
            return true; // deixa outras validações cuidarem disso
        }

        boolean exists = repository.existsByNameAndBarberId(
        		dto.getName(),
                dto.getBarber().getId()
        );

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Já existe uma especialidade com este nome para este barbeiro.")
                   .addPropertyNode("name")
                   .addConstraintViolation();
            return false;
        }

        return true;
    }
}