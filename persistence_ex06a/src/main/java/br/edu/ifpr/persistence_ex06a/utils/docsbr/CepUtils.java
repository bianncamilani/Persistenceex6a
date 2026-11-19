package br.edu.ifpr.persistence_ex06a.utils.docsbr;

public final class CepUtils {
   public static String format(String cep) {
      if (cep == null || cep.trim().isEmpty() || !cep.matches("\\d{8}"))
         return cep;
      // 99.999-999
      return cep.replaceAll("(\\d{2})(\\d{3})(\\d{3})","$1.$2-$3");
   }
}
