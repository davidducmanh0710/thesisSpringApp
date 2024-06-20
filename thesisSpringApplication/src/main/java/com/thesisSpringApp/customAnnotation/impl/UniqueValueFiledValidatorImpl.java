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
	private UserService userService;


	private String fieldName;

	@Override
	public void initialize(UniqueValueField constraintAnnotation) {
		this.fieldName = constraintAnnotation.fieldName();
		userService = ServiceUtils.getUserService();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		System.out.println(value);

		if (value == null || value.isEmpty() || value.equals(""))
			return true;

		UserService u = this.userService;

		boolean isUnique = false;
		switch (fieldName) {
		case "email":
			isUnique = !u.isUserExistsByEmail(value);
			break;
		case "phone":
			isUnique = !u.isUserExistsByPhone(value);
			break;
		case "useruniversityid":
			isUnique = !u.isUserExistsByUniversityId(value);
			break;
		default:
			throw new IllegalArgumentException("Unknown field name: " + fieldName);
		}

		return value != null && isUnique;
	}
}
