package com.example.storeeverything.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Target( { FIELD, METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = LowerCaseValidator.class)
public @interface LowerCaseValidation {
    String message() default "{lowerCase}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
