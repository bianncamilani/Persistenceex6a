package br.edu.ifpr.persistence_ex06a.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@ApplicationScoped
// esta classe será instanciada e operada pelo JBoss Weld.
public class AppService {
   private static final Logger LOG = LoggerFactory.getLogger(AppService.class);

   // objeto responsável por processar "persistence.xml" e validar e carregar
   // as configurações de acordo com o nome da unidade de persistência
   // (persistence-unit), além de conectar a aplicação com o servidor para
   // acesso a um determinado banco de dados
   @Inject
   EntityManagerFactory emf;
   // objeto responsável por realizar as operações CRUD (Create, Read, Update e
   // Delete) na base de dados.
   @Inject
   EntityManager        em;

   // A aplicação TODA será executada DENTRO deste método, pois ele é invocado
   // no contexto do "container" Weld para que objetos EntityManagerFactory e
   // EntityManager estejam disponíveis (criados antes deste método e liberados
   // após o seu término)
   public void run() {
      LOG.debug("===> MÉTODO run() <===");
   }
}
