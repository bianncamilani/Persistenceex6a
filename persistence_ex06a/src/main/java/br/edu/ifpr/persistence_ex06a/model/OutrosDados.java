package br.edu.ifpr.persistence_ex06a.model;

import java.util.Objects;
import java.util.UUID;

import br.edu.ifpr.persistence_ex06a.utils.docsbr.CepUtils;
import br.edu.ifpr.persistence_ex06a.utils.docsbr.CnpjUtils;
import br.edu.ifpr.persistence_ex06a.utils.docsbr.CpfUtils;
import br.edu.ifpr.persistence_ex06a.validations.annotations.CnpjConstraint;
import br.edu.ifpr.persistence_ex06a.validations.annotations.CpfConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="OUTROS_DADOS")
public class OutrosDados {
   @Id
   @GeneratedValue(strategy=GenerationType.UUID)
   @Column(name="OD_ID")
   private UUID id;

   @Column(name="OD_CEP")
   @NotEmpty(message="{cep.Empty.message}")
   @NotBlank(message="{cep.Blank.message}")
   @Pattern(regexp="(\\d{5})([0-9]{3})",message="{cep.Pattern.message}")
   private String cep;

   @Column(name="OD_CPF")
   @CpfConstraint
   private String cpf;

   @Column(name="OD_CNPJ")
   @CnpjConstraint
   private String cnpj;

   // relação bidirecional
//   @OneToOne(mappedBy="outros")
//   private Dados dados;
//
//   public Dados getDados() { return dados; }
//   public void setDados(Dados dados) { this.dados = dados; }

   public OutrosDados() {}
   public OutrosDados(String cep, String cpf, String cnpj) {
      this.cep  = cep;
      this.cpf  = cpf;
      this.cnpj = cnpj;
   }

   public UUID getId() { return id; }

   public String getCep() { return cep; }
   public void setCep(String cep) { this.cep = cep; }

   public String getCpf() { return cpf; }
   public void setCpf(String cpf) { this.cpf = cpf; }

   public String getCnpj() { return cnpj; }
   public void setCnpj(String cnpj) { this.cnpj = cnpj; }

   @Override
   public String toString() {
      return getClass().getSimpleName() + "[id=" + id + ", cep=" +
             CepUtils.format(cep) + ", cpf=" + CpfUtils.format(cpf) +
             ", cnpj=" + CnpjUtils.format(cnpj) +"]";
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;

      OutrosDados other = (OutrosDados) obj;

      return Objects.equals(id,other.id) &&
             Objects.equals(cep,other.cep) &&
             Objects.equals(cnpj,other.cnpj) &&
             Objects.equals(cpf,other.cpf);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id,cep,cnpj,cpf);
   }

   // ==========================================================================
   // os métodos a seguir implementam a API Fluente (Fluent API) seguindo o
   // padrão de projetos "Builder"
   // ==========================================================================
   public static Builder builder() {
	   return new Builder();
   }

   public static class Builder {
      private String cep;
      private String cpf;
      private String cnpj;

      public Builder cep(String cep) {
         this.cep = cep;

         return this;
      }

      public Builder cpf(String cpf) {
         this.cpf = cpf;

         return this;
      }

      public Builder cnpj(String cnpj) {
         this.cnpj = cnpj;

         return this;
      }

      public OutrosDados build() {
         return new OutrosDados(cep,cpf,cnpj);
      }
   }
}

/* JSON
{
   "cep": "86044750",
   "cpf": "32217302094",
   "cnpj": "81372622000142"
}
*/

/* XML
   <cep>86044750</cep>
   <cpf>32217302094</cpf>
   <cnpj>81372622000142</cnpj>
*/

/* YAML
   cep: 86044750
   cpf: 32217302094
   cnpj: 81372622000142
}
*/
