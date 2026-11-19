package br.edu.ifpr.persistence_ex06a.utils.docsbr;

import java.util.List;
import java.util.Random;

import br.edu.ifpr.persistence_ex06a.utils.docsbr.CpfStatus;
import br.edu.ifpr.persistence_ex06a.enums.UF;

public final class CpfUtils {

   private static final Random RANDOM = new Random();

   // Impede a instanciação da classe
   private CpfUtils() {
   }

   // VALIDAÇÃO
   public static CpfStatus validate(String cpf) {

       if (cpf == null || cpf.trim().isEmpty())
           return CpfStatus.CPF_INV;

       if (!cpf.matches("\\d{11}"))
           return CpfStatus.CPF_LEN;

       if (cpf.matches("(\\d)\\1{10}"))
           return CpfStatus.CPF_INV;

       String base = cpf.substring(0, 9);

       int dv1 = calcularDigito(base);
       if (dv1 != cpf.charAt(9) - '0')
           return CpfStatus.CPF_DV1;

       int dv2 = calcularDigito(base + dv1);
       if (dv2 != cpf.charAt(10) - '0')
           return CpfStatus.CPF_DV2;

       return CpfStatus.OK;
   }

   // FORMATAÇÃO
   public static String format(String cpf) {

       if (cpf == null || !cpf.matches("\\d{11}"))
           return cpf;

       return cpf.replaceFirst(
           "(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
           "$1.$2.$3-$4"
       );
   }

   // GERAÇÃO SEM INFORMAR ESTADO
   public static String generate() {
       return generate(null);
   }

   // GERAÇÃO INFORMANDO ESTADO
   public static String generate(UF uf) {

       StringBuilder cpf = new StringBuilder();

       // Gera os oito primeiros dígitos
       for (int i = 0; i < 8; i++)
           cpf.append(RANDOM.nextInt(10));

       // Gera o nono dígito conforme a UF
       cpf.append(obterDigitoUF(uf));

       // Calcula os dois dígitos verificadores
       cpf.append(calcularDigito(cpf.toString()));
       cpf.append(calcularDigito(cpf.toString()));

       return cpf.toString();
   }

   // DETERMINA O NONO DÍGITO CONFORME A UF
   private static int obterDigitoUF(UF uf) {

       if (uf == null)
           return RANDOM.nextInt(10);

       return switch (uf) {

           case DISTRITO_FEDERAL, GOIAS, MATO_GROSSO,
                MATO_GROSSO_SUL, TOCANTINS -> 1;

           case PARA, AMAZONAS, ACRE, AMAPA,
                RONDONIA, RORAIMA -> 2;

           case CEARA, MARANHAO, PIAUI -> 3;

           case PERNAMBUCO, ALAGOAS, PARAIBA,
                RIO_GRANDE_NORTE -> 4;

           case BAHIA, SERGIPE -> 5;

           case MINAS_GERAIS -> 6;

           case RIO_JANEIRO, ESPIRITO_SANTO -> 7;

           case SAO_PAULO -> 8;

           case PARANA, SANTA_CATARINA -> 9;

           case RIO_GRANDE_SUL -> 0;
       };
   }

   // CÁLCULO REUTILIZÁVEL DO DÍGITO VERIFICADOR
   private static int calcularDigito(String cpf) {

       int soma = 0;
       int peso = cpf.length() + 1;

       for (int i = 0; i < cpf.length(); i++) {
           soma += (cpf.charAt(i) - '0') * peso--;
       }

       int resto = 11 - (soma % 11);

       return (resto >= 10) ? 0 : resto;
   }

   // IDENTIFICA OS ESTADOS POSSÍVEIS
   public static List<UF> ufs(String cpf) {

       if (cpf == null || !cpf.matches("\\d{11}"))
           return List.of();

       int digito = cpf.charAt(8) - '0';

       return switch (digito) {

           case 1 -> List.of(
               UF.DISTRITO_FEDERAL, UF.GOIAS,
               UF.MATO_GROSSO, UF.MATO_GROSSO_SUL,
               UF.TOCANTINS
           );

           case 2 -> List.of(
               UF.ACRE, UF.AMAPA, UF.AMAZONAS,
               UF.PARA, UF.RONDONIA, UF.RORAIMA
           );

           case 3 -> List.of(
               UF.CEARA, UF.MARANHAO, UF.PIAUI
           );

           case 4 -> List.of(
               UF.ALAGOAS, UF.PARAIBA,
               UF.PERNAMBUCO, UF.RIO_GRANDE_NORTE
           );

           case 5 -> List.of(
               UF.BAHIA, UF.SERGIPE
           );

           case 6 -> List.of(UF.MINAS_GERAIS);

           case 7 -> List.of(
               UF.ESPIRITO_SANTO, UF.RIO_JANEIRO
           );

           case 8 -> List.of(UF.SAO_PAULO);

           case 9 -> List.of(
               UF.PARANA, UF.SANTA_CATARINA
           );

           case 0 -> List.of(UF.RIO_GRANDE_SUL);

           default -> List.of();
       };
   }
}
