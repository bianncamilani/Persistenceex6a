package br.edu.ifpr.persistence_ex06a.main;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class PersistenceEx06Main {
   public PersistenceEx06Main() {
      // inicializa e executa do contêiner CDI (Contexts and Dependency
      // Injection) para injeção de dependência (isto evita a criação explícita
      // de objetos pela aplicação)
      try (SeContainer container = SeContainerInitializer.newInstance()
                                                         .initialize()) {
         // recupera a instância da classe de serviço
         AppService service = container.select(AppService.class).get();
         // Executa a lógica da aplicação
         service.run();
      }
      // neste ponto o contêiner é liberado e os métodos anotados com
      // @PreDestroy e/ou parâmetros anotados com @Disposes nas classes
      // produtoras são invocados
	}

    public static void main(String[] args) {
       new PersistenceEx06Main();
    }
}
