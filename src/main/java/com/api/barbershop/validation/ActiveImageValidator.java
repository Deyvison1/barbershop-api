package com.api.barbershop.validation;

import java.util.Objects;

import com.api.barbershop.dto.HaircutDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ActiveImageValidator implements ConstraintValidator<HasActiveImage, HaircutDTO> {
	@Override
	public boolean isValid(HaircutDTO dto, ConstraintValidatorContext context) {
		if (Objects.nonNull(dto.getImages())) {
			long activeCount = dto.getImages().stream().filter(img -> Boolean.TRUE.equals(img.getActive())).count();

			if (activeCount == 0) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("É preciso informar pelo menos uma imagem ativa.")
						.addConstraintViolation();
				return false;
			} else if (activeCount > 1) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Não é permitido mais de uma imagem ativa por corte.")
						.addConstraintViolation();
				return false;
			}
		}
		return true;
	}

}
