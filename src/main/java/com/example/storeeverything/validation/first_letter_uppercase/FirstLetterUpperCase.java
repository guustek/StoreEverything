package com.example.storeeverything.validation.first_letter_uppercase;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Target({FIELD, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FirstLetterUpperCaseValidator.class)
public @interface FirstLetterUpperCase {

    String message() default "First letter must be uppercase";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
