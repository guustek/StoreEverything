package com.example.storeeverything.validation.lower_case;

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
@Constraint(validatedBy = LowerCaseValidator.class)
public @interface LowerCase {
    String message() default "Must contain only lowercase letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
