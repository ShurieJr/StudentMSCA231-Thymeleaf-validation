package shuuriye.studentmsca231.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {StudentEmailValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentEmail {  //annotation

    String message() default "email must be an educational email , ends with(just.edu.so)";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
