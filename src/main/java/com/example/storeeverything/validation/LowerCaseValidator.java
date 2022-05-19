package com.example.storeeverything.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class LowerCaseValidator implements ConstraintValidator<LowerCaseValidation, String> {
    @Override
    public void initialize(LowerCaseValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.equals(value.toLowerCase())){
            return true;
        }
        return false;
    }
}
