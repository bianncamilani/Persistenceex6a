package br.edu.ifpr.persistence_ex06a.main;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ifpr.persistence_ex06a.model.Dados;
import br.edu.ifpr.persistence_ex06a.model.OutrosDados;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class DadosIT {
   private static final Logger LOG = LoggerFactory.getLogger(DadosIT.class);

   private static EntityManagerFactory emf;

   private EntityManager em;
   // executa este método assim que a classe é carregada na memória
   @BeforeAll
   static void setupAll() {
      // conecta usando as configurações estáticas do persistence.xml
      emf = Persistence.createEntityManagerFactory("TESTE06");
   }
   // executa este método imediamente antes da classe ser "dispensada" do uso
   @AfterAll
   static void tearDownAll() {
      if (emf != null && emf.isOpen())
         emf.close();
   }
   // antes de cada método de teste (@Test), executada este método
   @BeforeEach
   void setUp() {
      if (emf != null && emf.isOpen())
         em = emf.createEntityManager();
   }
   // depois de cada método de teste (@Test), executada este método
   @AfterEach
   void tearDown() {
      if (em != null && em.isOpen())
         em.close();
   }

   @Test
   @DisplayName(
      "Deve excluir o 'Dados' e em cascata o seu 'OutroDados' (Composição 1:1)"
   )
   void testExclusaoEmCascataComposicao() {
      // garantimos que "emf" seja válido
      assertNotNull(emf,"'EntityManagerFactory' inválido");
      // garantimos que "em" seja válido
      assertNotNull(em,"'EntityManager inválido'");

      OutrosDados outros = OutrosDados.builder()
                                      .cep("86050450")
                                      .cpf("98305931005")
                                      .cnpj("33484122000150").build();

      Dados dados = new Dados();

      dados = dados.nome("Grace Murray Hopper")
                   .idade(86)
                   .valido(Boolean.TRUE)
                   .nascimento(LocalDate.of(1906,Month.DECEMBER,9))
                   .email("grace.hopper@cc.com");
      dados.setCargos(new String[] { "CARGO DOIS" });
      dados.setHabilidades(Arrays.asList("HABILITADADE DOIS"));
      dados.setSalarios(Map.of("B", new BigDecimal("2310")));
      dados.setOutrosDados(outros);

	  LOG.info("===> CREATE (testExclusaoEmCascataComposicao) <===");
	  // 1) INSERT
      em.getTransaction().begin();
      em.persist(dados);
      em.getTransaction().commit();

      UUID idDados  = dados.getId();
      UUID idOutros = outros.getId();
      assertNotNull(idDados,"O ID(Dados) deve ser gerado após a inclusão");
      assertNotNull(idOutros,"O ID(Outros) deve ser gerado após a inclusão");

      em.clear();

      LOG.info("===> DELETE (testExclusaoEmCascataComposicao) <===");
      // 2) DELETE
      em.getTransaction().begin();
      dados = em.find(Dados.class,idDados);
      assertNotNull(dados,"'Dados' deve existir antes da remoção");
      em.remove(dados);
      em.getTransaction().commit();

      em.clear();
      // 3) verifica a NÃO existência
      dados  = em.find(Dados.class,idDados);
      outros = em.find(OutrosDados.class,idOutros);
      assertNull(dados,"'Dados' deve ter sido excluído");
      assertNull(outros,"'Outros' deve ter sido excluído");
   }
}
