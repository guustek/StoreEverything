package com.example.storeeverything.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LowerCaseValidator implements ConstraintValidator<LowerCaseValidation, String> {
    @Override
    public void initialize(LowerCaseValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals(value.toLowerCase());
    }
}
