package br.edu.ifpr.persistence_ex06a.utils.docsbr;

import java.util.Random;

public final class CnpjUtils {
   
   public static CnpjStatus validate(String cnpj) {
      if (cnpj == null || cnpj.trim().isEmpty()) return CnpjStatus.CNPJ_INV;

      if (!cnpj.matches("\\d{14}")) return CnpjStatus.CNPJ_LEN;
      //Validação
      for (int i = 1; i < 10; i++) {
         String temp = (String.valueOf(i)).repeat(14);

         if (cnpj.equals(temp)) return CnpjStatus.CNPJ_INV;
      }

      int digitosAMenos = 2, posicaoDV = 12, peso = 5;
      for (int passos = 1; passos <= 2; passos++) {
         int soma = 0;
         for (int i = 0; i < (cnpj.length() - digitosAMenos); i++) {
            soma += peso-- * (cnpj.charAt(i) - 48);

            if (peso < 2) peso = 9;
         }
         int resto = 11 - (soma % 11);
         int dv    = (resto == 10) ? 0 : resto;

         if (dv != (cnpj.charAt(posicaoDV) - 48))
            if (passos == 1) return CnpjStatus.CNPJ_DV1;
            else return CnpjStatus.CNPJ_DV2;

         --digitosAMenos;
         ++posicaoDV;
         peso = 6;
      }
      return CnpjStatus.OK;
   }
   
   // Formatação
   public static String format(String cnpj) {
      if (cnpj == null || cnpj.trim().isEmpty() || cnpj.length() != 14)
         return cnpj;
      // 99.999.999/9999-99
      return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})",
                             "$1.$2.$3/$4-$5");
   }
   
// Gera um CNPJ válido
   public static String gerarCNPJ() {
   
       Random random = new Random();
   
       String cnpj = "";
   
       // Gera os 8 primeiros números
       for (int i = 0; i < 8; i++) {
           cnpj += random.nextInt(10);
       }
       // Concatena o 0001 para simular a realidade
       cnpj = cnpj + "0001";
   
       // Calcula os dois dígitos verificadores
       cnpj += calcularDigito(cnpj);
       cnpj += calcularDigito(cnpj);
   
       return cnpj;
}

// Calcula um dígito verificador
   private static int calcularDigito(String cnpj) {
      
      int soma = 0;
      int peso;
      
      if (cnpj.length() == 12) {
      peso = 5;
      } else {
      peso = 6;
      }
      
      for (int i = 0; i < cnpj.length(); i++) {
      soma += (cnpj.charAt(i) - 48) * peso;
      
      peso--;
      
      if (peso < 2) {
      peso = 9;
         }
      }
      int resto = 11 - (soma % 11);
      return (resto == 10) ? 0 : resto;
      }
}
