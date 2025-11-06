package com.api.barbershop.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UniqueSpecialtyNameByBarberValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueSpecialtyNameByBarber {
    String message() default "Já existe uma especialidade com este nome para este barbeiro.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
