package br.edu.ifpr.persistence_ex06a.validations.annotations;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.edu.ifpr.persistence_ex06a.validations.validations.CnpjValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR })
@Constraint(validatedBy={ CnpjValidation.class })
@NotEmpty(message="{cnpj.Empty.message}")
@NotBlank(message="{cnpj.Blank.message}")
@Pattern(regexp="\\d{14}", message="{cnpj.Length.message}")
public @interface CnpjConstraint {
   String message() default "{cnpj.Default.message}";

   Class<?>[] groups() default { };

   Class<? extends Payload>[] payload() default { };
}
