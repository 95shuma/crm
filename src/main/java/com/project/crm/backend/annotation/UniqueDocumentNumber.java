package com.project.crm.backend.annotation;

import com.project.crm.backend.annotation.validator.UniqueDocumentNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDocumentNumberValidator.class)
public @interface UniqueDocumentNumber {
    String message() default "Этот номер паспорта уже используется другим пользователем";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

