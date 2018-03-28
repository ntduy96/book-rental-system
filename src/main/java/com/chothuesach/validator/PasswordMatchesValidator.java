package com.chothuesach.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chothuesach.dto.NguoiDungDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		NguoiDungDto nguoiDungDto = (NguoiDungDto) obj;
		return nguoiDungDto.getPassword().equals(nguoiDungDto.getConfirmPassword());
	}

}
