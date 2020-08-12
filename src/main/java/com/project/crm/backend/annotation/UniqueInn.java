package com.project.crm.backend.annotation;

import com.project.crm.backend.annotation.validator.UniqueInnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueInnValidator.class)
public @interface UniqueInn {
    String message() default "Этот ИНН уже используется другим пользователем";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

