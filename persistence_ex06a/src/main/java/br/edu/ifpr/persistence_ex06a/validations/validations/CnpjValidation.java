package br.edu.ifpr.persistence_ex06a.validations.validations;

import br.edu.ifpr.persistence_ex06a.utils.docsbr.CnpjStatus;
import br.edu.ifpr.persistence_ex06a.utils.docsbr.CnpjUtils;
import br.edu.ifpr.persistence_ex06a.validations.annotations.CnpjConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidation implements
                            ConstraintValidator<CnpjConstraint, String> {
   @Override
   public void initialize(CnpjConstraint constraintAnnotation) {}

   @Override
   public boolean isValid(String cnpj, ConstraintValidatorContext context) {
      return CnpjUtils.validate(cnpj) == CnpjStatus.OK;
   }
}
