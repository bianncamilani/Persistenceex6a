package br.edu.ifpr.persistence_ex06a.validations.processors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import jakarta.validation.Configuration;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ElementKind;
import jakarta.validation.Path;
import jakarta.validation.Path.Node;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidationProcessor {
   private ValidatorFactory factory;
   private Validator        validator;

   public ValidationProcessor() {
      Configuration<?> config = Validation.byDefaultProvider().configure();

      factory   = config.buildValidatorFactory();
      validator = factory.getValidator();
   }

   public <T> Map<String, List<String>> validate(T t, Class<?>... groups) {
      if (t == null)
         throw new IllegalArgumentException("Objeto inválido para validação");

      Set<ConstraintViolation<T>> violations =
    			             (groups.length > 0 ) ? validator.validate(t,groups)
    			            			          : validator.validate(t);

      Map<String, List<String>>   errors     = new TreeMap<>();

      for (ConstraintViolation<T> violation : violations) {
         String property = customizePath(violation.getPropertyPath());

         if (!errors.containsKey(property))
            errors.put(property,new ArrayList<>());

         errors.get(property).add(violation.getMessage());
      }
      return errors;
   }

   // Método auxiliar para customizar o caminho da propriedade de uma violação
   // (erro) de validação.
   // Transforma caminhos como "membros[0].<element_da_lista>" para "members".
   // Também lida com violações diretas em propriedades simples ou na própria
   // coleção.
   private String customizePath(Path path) {
      StringBuilder  customPath = new StringBuilder();
      Iterator<Node> iterator   = path.iterator();

      while (iterator.hasNext()) {
         Node node = iterator.next();

         // se o nó é uma propriedade (atributo simples da classe)
         if (node.getKind() == ElementKind.PROPERTY) {
            if (customPath.length() > 0)
               // Adiciona separador de ponto se já houver algo
               customPath.append(".");

            customPath.append(node.getName());
         }
         // se o nó indica que estamos em um elemento dentro de uma coleção
         // (ex: o "[0]" ou "<element_da_lista>" em
         // "membros[0].<elemento_da_lista>")
         else
            if (node.isInIterable() ||
                node.getKind() == ElementKind.CONTAINER_ELEMENT) {
               // Se a violação está em um item da lista, queremos retornar
               // apenas o nome da lista.
               // O 'customPath' já deve conter o nome da propriedade da lista
               // (ex: "calendario").
               return customPath.toString(); // Retorna "calendario"
            }
            // Para outros tipos de nó (ex: BEAN para validações a nível de
            // classe), ou se for o último nó e não um elemento de coleção, o
            // loop continua ou o caminho acumulado será retornado no final.
      }
      // Se o loop terminar, significa que a violação não foi em um elemento de
      // coleção. O 'customPath' já terá o nome da propriedade ou o caminho
      // completo. Ex: para @NotNull ou @NotEmpty na própria lista 'calendario',
      // ou para 'emissor', 'numero'.
      return customPath.toString();
   }
}
