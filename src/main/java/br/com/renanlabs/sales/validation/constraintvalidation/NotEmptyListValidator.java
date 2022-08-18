package br.com.renanlabs.sales.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.renanlabs.sales.validation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{

	@Override
	public boolean isValid(List list, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return list != null && !list.isEmpty();
	}
	
	@Override
	public void initialize(NotEmptyList contraintAnnotation) {
		
	}

}
