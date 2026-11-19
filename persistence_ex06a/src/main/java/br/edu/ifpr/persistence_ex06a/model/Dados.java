package br.edu.ifpr.persistence_ex06a.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import br.edu.ifpr.persistence_ex06a.model.validations.grpups.OutrosDadosGroup;
import br.edu.ifpr.persistence_ex06a.model.validations.grpups.OutrosDadosInsertGroup;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;

@Entity
@Table(name="DADOS")
public class Dados {
   @Id
   @GeneratedValue(strategy=GenerationType.UUID)
   @Column(name="DAD_ID")
   private UUID id;

   @Column(name="DAD_NOME", nullable=false)
   @NotEmpty(message="Nome deve possuir conteúdo")
   @Size(max=100, message="Nome não deve possuir mais de 100 caracters")
   private String nome;

   @Column(name="DAD_IDADE", nullable=false)
   @NotNull
   @Min(value=18, message="Idade deve ser maior ou igual a {value}")
   @Max(value=150, message="Idade deve ser menor ou igual a {value}")
   private Integer idade;

   @Column(name="DAD_VALIDO")
   @AssertTrue
   private Boolean valido;

   @Column(name="DAD_DT_NASCIMENTO", nullable=false)
   @NotNull @Past
   private LocalDate nascimento;

   @Column(name="DAD_EMAIL", nullable=false)
   @NotNull @Email
   private String email;

   // composição 1:1: propaga todas as operações e remove órfãos automaticamente
   @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
   @JoinColumn(name="OD_ID", foreignKey=@ForeignKey(name="DADOS_OUTROSD_FK"))
   // ignorado por padrão, avaliado apenas se o grupo "OutrosDadosGroup" for
   // ativado
   @NotNull(groups={OutrosDadosInsertGroup.class})
   @Valid
   // ignorado por padrão, avaliado apenas se o grupo "OutrosDadosGroup" for
   // ativado
   @ConvertGroup(from=Default.class, to=OutrosDadosGroup.class)
   private OutrosDados outros;

   // neste exemplo a relação 1..N NÃO é persistida e é usada somente para
   // demonstrar a especificação Jakarta Validation
   @Transient
   @NotEmpty
   private String[] cargos;

   // neste exemplo a relação 1..N NÃO é persistida e é usada somente para
   // demonstrar a especificação Jakarta Validation
   @Transient
   @NotEmpty
   private List<@NotEmpty String> habilidades;

   // neste exemplo a relação 1..N NÃO é persistida e é usada somente para
   // demonstrar a especificação Jakarta Validation
   @Transient
   @NotEmpty
   private Map<@NotEmpty String,
               @DecimalMin(value="1320") BigDecimal> salarios;

   // o construtor default DEVE existir para Jakarta Persistence
   // com o objeto construído é preciso invocar os métodos 'setters' para
   // popular seu estado
   public Dados() {
      this.habilidades = new ArrayList<>();
      this.salarios    = new HashMap<>();
   }

   public UUID getId() { return id; }
//   public void setId(UUID id) { this.id = id; }

   public String getNome() { return nome; }
   public void setNome(String nome) { this.nome = nome; }

   public Integer getIdade() { return idade; }
   public void setIdade(Integer idade) { this.idade = idade; }

   public Boolean isValido() { return valido; }
   public void setValido(Boolean valido) { this.valido = valido; }

   public LocalDate getNascimento() { return nascimento; }
   public void setNascimento(LocalDate nascimento) {
      this.nascimento = nascimento;
   }

   public String getEmail() { return email; }
   public void setEmail(String email) { this.email = email; }

   public OutrosDados getOutrosDados() { return outros; }
   public void setOutrosDados(OutrosDados outros) { this.outros = outros; }

   public String[] getCargos() { return cargos; }
   public void setCargos(String[] cargos) { this.cargos = cargos; }

   public List<String> getHabilidades() { return habilidades; }
   public void setHabilidades(List<String> habilidades) {
      // eliminamos as "habilidades" existentes antes de incluir as novas
      this.habilidades.clear();
      this.habilidades.addAll(habilidades);
   }

   public Map<String, BigDecimal> getSalarios() { return salarios; }
   public void setSalarios(Map<String, BigDecimal> salarios) {
      // eliminamos os "salarios" existentes antes de incluir os novos
      this.salarios.clear();
      this.salarios.putAll(salarios);
   }

   @Override
   public String toString() {
      return getClass().getSimpleName() + " [nome=" + nome + ", idade=" +
             idade + ", valido=" + valido + ", nascimento=" + nascimento +
             ", email=" + email + ", outros=" + outros + ", cargos=" +
             cargos.length + ", habilidades=" + habilidades.size()+
             ", salarios=" + salarios.size() + "]";
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;

      Dados other = (Dados) obj;

      return Objects.equals(nome,other.nome) &&
             Objects.equals(idade,other.idade) &&
             Objects.equals(valido,other.valido) &&
             Objects.equals(nascimento,other.nascimento) &&
             Objects.equals(email,other.email) &&
             Objects.equals(outros,other.outros) &&
             Arrays.equals(cargos,other.cargos) &&
             Objects.equals(habilidades,other.habilidades) &&
             Objects.equals(salarios,other.salarios);
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(cargos);
      result = prime * result + Objects.hash(nome,idade,valido,nascimento,email,
                                             outros,habilidades,salarios);
      return result;
   }

   // ==========================================================================
   // os métodos a seguir implementam a API Fluente (Fluent API), que facilita
   // a construção de objetos
   // USAR ESTE TIPO DE IMPLEMENTAÇÃO DEVE SER FEITA COM CAUTELA, POIS PODE
   // LEVAR A CLASSES COMPLEXAS SEM NECESSIDADE, OU SEJA, É UMA DECISÃO (BEM
   // PENSADA) DE PROJETO
   // PARA CASOS EM QUE SE PRESA PELA IMUTABILIDAE (COMO A CLASSE String DO
   // JAVA), É POSSÍVEL IMPLEMENTAR A FLUENT API SEGUINDO O PADRÃO DE PROJETOS
   // "Builder"
   // ==========================================================================
   public Dados nome(String nome) {
      this.nome = nome;
      // retornar "this" é retornar uma instância da própria classe, o que
      // permite o encadeamento de métodos para criar o objeto final
      return this;
   }
   public Dados idade(Integer idade) {
      this.idade = idade;

      return this;
   }
   public Dados valido(Boolean valido) {
      this.valido = valido;

      return this;
   }
   public Dados nascimento(LocalDate nascimento) {
      this.nascimento = nascimento;

      return this;
   }
   public Dados email(String email) {
      this.email = email;

      return this;
   }
}

/* JSON
   {
      "nome": "José Joaquim da Silva Xavier",
      "idade": 39,
      "valido": true,
      "nascimento": "1900-12-31",
      "email": "johndoe@server.com",
      "outrosDados": {
         "cep": "86044750",
         "cpf": "32217302094",
         "cnpj": "81372622000142"
      }, 
      "cargos": [
         "Fundador",
         "CTO",
         "Author"
      ],
      "habilidades": [
         "Java",
         "Python",
         "Kotlin",
         "JavaScript"
      ],
      "salarios": {
         "2022": 20000,
         "2021": 15000,
         "2020": 10000
      }
   }
*/

/* XML
   <dados>
      <nome>José Joaquim da Silva Xavier</nome>
      <idade>39</idade>
      <valido>true</valido>
      <nascimento>1900-12-31</nascimento>
      <email>johndoe@server.com</email>
      <outrosDados>
         <cep>86044750</cep>
         <cpf>32217302094</cpf>
         <cnpj>81372622000142</cnpj>
      </outrosDados>
      <cargos>
         <cargos>Fundador</cargos>
         <cargos>CTO</cargos>
         <cargos>Author</cargos>
      </cargos>
      <habilidades>
         <habilidades>Java</habilidades>
         <habilidades>Python</habilidades>
         <habilidades>Kotlin</habilidades>
         <habilidades>JavaScript</habilidades>
      </habilidades>
      <salarios>
         <A2022>20000</A2022>
         <A2021>15000</A2021>
         <A2020>10000</A2020>
      </salarios>
   </dados>
*/

/* YAML
nome: José Joaquim da Silva Xavier
idade: 39
valido: true
nascimento: 1900-12-31
email: johndoe@server.com
outrosDados:
   cep: 86044750
   cpf: 32217302094
   cnpj: 81372622000142
cargos: [Fundador, CTO, Author]
habilidades: [Java, Python, Kotlin, JavaScript]
salarios: {A2022=20000, A2021=15000, A2020=10000}
*/
