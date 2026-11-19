package br.edu.ifpr.persistence_ex06a.validations.annotations;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.edu.ifpr.persistence_ex06a.utils.docsbr.CpfStatus;
import br.edu.ifpr.persistence_ex06a.utils.docsbr.CpfUtils;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR })
@Constraint(validatedBy={ CpfConstraint.Validator.class })
@NotEmpty(message="{cpf.Empty.message}")
@NotBlank(message="{cpf.Blank.message}")
@Pattern(regexp="\\d{11}", message="{cpf.Length.message}")
public @interface CpfConstraint {
   String message() default "{cpf.Default.message}";

   Class<?>[] groups() default { };

   Class<? extends Payload>[] payload() default { };

   class Validator implements ConstraintValidator<CpfConstraint, String> {
      @Override
      public void initialize(CpfConstraint constraintAnnotation) {}

      @Override
      public boolean isValid(String cpf, ConstraintValidatorContext context) {
         return CpfUtils.validate(cpf) == CpfStatus.OK;
      }
   }
}
