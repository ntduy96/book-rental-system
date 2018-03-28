package com.chothuesach.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CmndValidator implements ConstraintValidator<ValidCmnd, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.length() == 9 || value.length() == 12) {
			return true;
		}
		return false;
	}

}
