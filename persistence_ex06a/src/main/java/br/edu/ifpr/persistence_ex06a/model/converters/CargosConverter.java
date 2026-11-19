package br.edu.ifpr.persistence_ex06a.model.converters;

import jakarta.persistence.AttributeConverter;

public class CargosConverter implements AttributeConverter<String[], String> {
   // mudar o delimitador (caractere que separa as strings conforme necessidade
   private static final String DELIMITER = ",";

   @Override
   public String convertToDatabaseColumn(String[] attribute) {
      if (attribute == null || attribute.length == 0)
         return null;
      // concatena ("junta") os elementos do array em uma string usando como
      // delimitador (separador) o caractere definido na constante 'DELIMITER'
      return String.join(DELIMITER,attribute);
   }

   @Override
   public String[] convertToEntityAttribute(String dbData) {
      if (dbData == null || dbData.trim().isEmpty())
         // retornar um array vazio, o que ajuda a evitar "NullPointerException"
         // se "null" fosse retornado
         return new String[0];
      // ao ler a string do SGBD-R, o 'provider' Jakarta Persistence invoca
      // este método para que ela seja separada em elementos de um array
      return dbData.split(DELIMITER);
   }
}
