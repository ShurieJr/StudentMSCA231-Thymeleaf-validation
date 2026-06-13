package shuuriye.studentmsca231.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StudentEmailValidator
        implements ConstraintValidator<StudentEmail , String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {
        if(value==null || value.isEmpty()){
            return true;
        }
        return value.toLowerCase().endsWith("just.edu.so");
    }
}
