# Persistenceex6a

🧪 Avaliação — Testes Unitários com JUnit (persistence_ex06a)

Projeto desenvolvido como método de avaliação da disciplina de Construção de Aplicações Orientadas a Objetos no Instituto Federal do Paraná (IFPR).

🎯 Objetivo

O foco principal desta atividade é a criação, implementação e validação de testes unitários utilizando JUnit para verificar o comportamento das regras de negócio, validações e utilitários do sistema.

🔍 O que está sendo testado

A suíte de testes cobre os componentes de validação e utilitários do projeto:

Validação de Documentos Brasileiros (utils.docsbr):

CpfTest.java: Testes unitários para validação, formatação e verificação de status de CPF (CpfUtils, CpfStatus).

CnpjTest.java: Testes unitários para validação, formatação e verificação de status de CNPJ (CnpjUtils, CnpjStatus).

Regras de Domínio, Persistência e Bean Validation:

Validações customizadas (@CpfConstraint, @CnpjConstraint), conversores (CargosConverter), listeners (DadosValidationListener) e entidades (Dados, OutrosDados).

📂 Estrutura de Testes e Código

persistence_ex06a/
├── pom.xml                                      # Configurações do Maven e dependências (JUnit, JPA, CDI, Validator)
└── src/
    ├── main/java/br/edu/ifpr/persistence_ex06a/
    │   ├── enums/                               # Enumeração de UFs
    │   ├── main/                                # Classes principais da aplicação
    │   ├── model/                               # Entidades, Conversores, Grupos e Listeners
    │   ├── utils/                               # Utilitários de documentos (CPF, CNPJ, CEP) e CDI
    │   └── validations/                         # Anotações e validadores customizados
    └── test/java/br/edu/ifpr/persistence_ex06a/
        ├── main/                                # Testes das entidades e persistência (DadosIT, OutrosDadosIT)
        └── utils/docsbr/                        # Testes unitários JUnit (CpfTest, CnpjTest)


▶️ Como Executar os Testes

Via IDE (Eclipse / IntelliJ / NetBeans)

Clique com o botão direito sobre o projeto persistence_ex06a.

Selecione Run As > Maven build... (ou abra a janela de execução do Maven).

No campo Goals, informe:

clean test


Clique em Run e acompanhe o relatório de execução do JUnit no console.

Via Terminal (Linha de Comando)

Na raiz do projeto (onde está localizado o arquivo pom.xml), execute:

mvn clean test


Os relatórios gerados pelo Maven Surefire podem ser conferidos em target/surefire-reports/.

🎓 Informações Acadêmicas

Instituição: Instituto Federal do Paraná (IFPR)

Disciplina: Construção de Aplicações Orientadas a Objetos

Atividade: Avaliação de Testes Unitários com JUnit****
