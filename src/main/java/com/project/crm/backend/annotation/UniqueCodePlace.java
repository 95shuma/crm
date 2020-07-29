package com.project.crm.backend.annotation;

import com.project.crm.backend.annotation.validator.UniqueCodePlaceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCodePlaceValidator.class)
public @interface UniqueCodePlace{
    String message() default "Этот код места уже используется";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

