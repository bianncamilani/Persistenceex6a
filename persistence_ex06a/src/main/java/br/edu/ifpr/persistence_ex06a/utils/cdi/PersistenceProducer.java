package br.edu.ifpr.persistence_ex06a.utils.cdi;

import java.util.logging.Logger;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
// esta classe serve como um produtor de objetos EntityManagerFactory e
// EntityManager para que a aplicação acesse uma base de dados usando Jakarta
// Persistence
public class PersistenceProducer {
   private static final Logger LOG = Logger.getLogger(
			                               PersistenceProducer.class.getName());

   private static final String PERSISTENCE_UNIT = "TESTE05";

   private EntityManagerFactory emf;
   // após a instância desta classe ser criada, o método a seguir será invocado,
   // anotação @PostConstruct, para realizar algum processamento, neste caso,
   // tentar criar o objeto EntityManagerFactory, que carregará e processará o
   // arquivos "persistence.xml" para a unidade de persistência nomeada.
   @PostConstruct
   public void init() {
      try {
         this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
      }
      catch (Exception e) {
         LOG.severe("Falha ao inicializar o EntityManagerFactory: " +
                    e.getMessage());
         // garante que fica null em caso de falha/erro, desta forma a aplicação
         // poderá realizar algum processamento ou ser encerrada "graciosamente"
         this.emf = null;
       }
   }

   @Produces
   // permite retornar null sem disparar IllegalProductException no Weld por
   // conta da anotação @Dependent
   @Dependent
   public EntityManagerFactory produceFactory() {
      return this.emf;
   }
   // antes que o JBoss Weld libere a instância desta classe, este método,
   // anotado com @PreDestroy, será invocado para realizar algum processamento,
   // neste caso, tentar encerrar o vínculo com o arquivo "persistence.xml".
   @PreDestroy
   public void closeFactory() {
      if (emf != null && emf.isOpen())
         emf.close();
      // Encerra explicitamente a thread de limpeza do MySQL Connector/J
      try { AbandonedConnectionCleanupThread.uncheckedShutdown(); }
      catch (Exception e) {
         LOG.warning("Falha ao encerrar a thread de limpeza do SGBD-R: " +
                     e.getMessage());
      }
   }
   // se o objeto EntityManagerFactory foi criado com sucesso, este método será
   // invocado para a criação do objeto EntityManager.
   @Produces
   @Dependent
   public EntityManager produceEntityManager(EntityManagerFactory emf) {
      if (emf == null || !emf.isOpen()) {
         LOG.warning("EntityManagerFactory indisponível.");

         return null; // O escopo @Dependent permite retornar null aqui
      }
      try { return emf.createEntityManager(); }
      catch (Exception e) {
         LOG.severe("Falha ao criar EntityManager: " + e.getMessage());

         return null;
      }
   }
   // antes que o JBoss Weld libere a instância desta classe, este método, cujo
   // parâmetro está anotado com @Disposes, será invocado para realizar algum
   // processamento, neste caso, tentar encerrar a conexão com o servidor de
   // bancos de dados.
   public void closeEntityManager(@Disposes EntityManager em) {
      if (em != null && em.isOpen())
         em.close();
   }
}
