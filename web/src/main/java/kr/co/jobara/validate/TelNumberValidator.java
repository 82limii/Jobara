package kr.co.jobara.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelNumberValidator implements ConstraintValidator<TelNumber, String> {

	private TelNumber annotation;
	
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String targetProp, ConstraintValidatorContext arg1) {
		boolean valid = targetProp==null || targetProp.isEmpty();
		if(!valid) {
			String regex = annotation.value();
			valid = targetProp.matches(regex);
		}
		return valid;
	}

}







