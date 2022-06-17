package com.example.storeeverything.validation.first_letter_uppercase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstLetterUpperCaseValidator implements ConstraintValidator<FirstLetterUpperCase, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ! s.isEmpty() && String.valueOf(s.charAt(0)).equals(String.valueOf(s.charAt(0)).toUpperCase());
    }
}
