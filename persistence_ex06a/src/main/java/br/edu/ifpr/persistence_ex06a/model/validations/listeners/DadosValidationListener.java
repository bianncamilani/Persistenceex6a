package br.edu.ifpr.persistence_ex06a.model.validations.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ifpr.persistence_ex06a.model.Dados;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class DadosValidationListener {
   private static final Logger LOG =
			             LoggerFactory.getLogger(DadosValidationListener.class);

   @PrePersist
   public void prePersist(Dados dados) {
      LOG.debug("%%%%% prePersist() %%%%%");
   }

   @PreUpdate
   public void preUpdate(Dados dados) {
      LOG.debug("&&&&& preUpdate() &&&&&");
   }

   @PreRemove
   public void preRemove(Dados dados) {
      LOG.debug("***** preRemove() *****");
   }
}
