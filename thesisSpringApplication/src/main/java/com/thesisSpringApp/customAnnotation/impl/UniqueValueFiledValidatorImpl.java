package com.thesisSpringApp.customAnnotation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thesisSpringApp.customAnnotation.UniqueValueField;
import com.thesisSpringApp.service.UserService;

@Component
public class UniqueValueFiledValidatorImpl implements ConstraintValidator<UniqueValueField, String> {

	@Autowired
	public UserService userService;


	private String fieldName;

	@Override
	public void initialize(UniqueValueField constraintAnnotation) {
		this.fieldName = constraintAnnotation.fieldName();
		userService = ServiceUtils.getUserService();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {


		if (value == null || value.isEmpty() || value.equals(""))
			return true;


		boolean isUnique = false;
		switch (fieldName) {
		case "email":
			isUnique = !userService.isUserExistsByEmail(value);
			break;
		case "phone":
			isUnique = !userService.isUserExistsByPhone(value);
			break;
		case "useruniversityid":
			isUnique = !userService.isUserExistsByUniversityId(value);
			break;
		default:
			throw new IllegalArgumentException("Unknown field name: " + fieldName);
		}

		return isUnique;
	}
}
