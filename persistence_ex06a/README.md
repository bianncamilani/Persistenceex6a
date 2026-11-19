
<!--
Markdown Cheatsheet
https://github.com/adam-p/markdown-here/wiki/markdown-cheatsheet
-->

## 🚀 Persistence - Exemplo 06 🚀

## 📌 Descrição do Projeto
* Exemplifica validações e testes de integração usando MySQL para persistência;
* Exemplifica a relação 1:1 seguindo o conceito de **Composição (UML)**. Um objeto `Dados` possui<br />
(é **entidade forte** da relação) um objeto `OutrosDados`. Quando um objeto `OutrosDados` é criado,<br />
 ele deve ser vinculado a um objeto `Dados`. Ao incluir/alterar o segundo, o primeiro será incluído/alterado<br />
 conjuntamente. Essa configuração é possível com o uso das anotações `OneToOne` e `JoinColumn` na classe<br />
 `Dados`;
* As configurações do  _provider_  Jakarta Persistence encontram-se no arquivo `persisence.xml`,<br />
localizado em `src/main/resources/META-INF`;
* As configurações de  _logging_  encontram-se no arquivo `logback.xml`, localizado em `src/main/resources`;
* As mensagens de valição encontram-se no arquivo `ValidationMessages_pt_BR.properties`, localizado<br />
em `src/main/resources`;
* A classe para teste de integração está localizada em `src/test/java`.

## 📋 Os testes de integração podem ser executados a partir<br />do Eclipse IDE ou em linha de comando (CLI)

### ⚙️ Eclipse IDE
1. Clicar com o botão direito do mouse no nome do projeto
2. Selecionar **Run As** > **Maven build...**
3. Na aba **JRE** digitar `--enable-native-access=ALL-UNNAMED` no campo **VM arguments**
4. Na aba **Main** digitar `clean verify` no campo **Goals**
5. Clicar no botão **Run**

Executar os passos acima somente na primeira execução.<br /><br />
A partir da segunda execução, clicar na "pontinha de flecha" ao lado direito do botão  _play_  na barra de botões.

Para executar testes para somente uma classe, digite `clean verify -Dit.test=DadosOutrosDadosIT`, no passo<br />
**4** acima, ou qualquer outro nome de classe para a qual os testes devam ser executados.

### ️⚙️ CLI ( _Command Line Interface_ )
No diretório raíz do projeto executar

```bash
mvn clean verify
```

## 📋 A aplicação pode ser executada a partir do Eclipse IDE<br />ou em linha de comando (CLI)

### ⚙️ Eclipse IDE
1. Clicar com o botão direito do mouse no nome do projeto
2. Selecionar **Run As** > **Maven build...**
3. Na aba **JRE** digitar `--enable-native-access=ALL-UNNAMED` no campo **VM arguments**
4. Na aba **Main** digitar `clean package exec:java -Pexec` no campo **Goals**
5. Clicar no botão **Run**

Executar os passos acima somente na primeira execução.<br /><br />
A partir da segunda execução, clicar na "pontinha de flecha" ao lado direito do botão  _play_  na barra de botões.

### ️⚙️ CLI ( _Command Line Interface_ )
1. Clicar com o botão direito do mouse no nome do projeto
2. Selecionar **Run As** > **Maven build...**
3. Na aba **JRE** digitar `--enable-native-access=ALL-UNNAMED` no campo **VM arguments**
4. Na aba **Main** digitar `clean package -Pcli` no campo **Goals**
5. Clicar no botão **Run**

Executar os passos acima somente na primeira execução.<br /><br />
A partir da segunda execução, clicar na "pontinha de flecha" ao lado direito do botão  _play_  na barra de botões.

No diretório `target` do projeto digitar o comando a seguir para executar a aplicação:

```bash
java -jar persistence_ex06.jar
```

Em ambos os casos acima, o Eclipse, usando o Apache Maven, criará o arquivo JAR (Java ARchive) no diretório<br />
`target` e as bibliotecas necessárias à execução da aplicação serão copiadas para `target/libs` (o diretório `libs`<br />
está configurado no arquivo `pom.xml`).

Para executar a aplicação em outro local do computador ou em outro computador são necessários o arquivo JAR e<br />
o diretório `libs`.

## 🛠️ Tecnologias
- **Linguagem:** Java
- **Especificações & Implementações:**
    * [Jakarta Persistence](https://jakarta.ee/specifications/persistence/3.2/) / [EclipseLink](https://eclipse.dev/eclipselink/) /  _driver_  [JDBC para MySQL](https://github.com/mysql/mysql-connector-j)
    * [Jakarta Contexts and Dependency Injection (CDI)](https://jakarta.ee/specifications/cdi/4.1/) / [JBoss Weld](https://weld.cdi-spec.org/)
    * [Jakarta Validation](https://jakarta.ee/specifications/bean-validation/3.1/) / [Hibernate Validator](https://hibernate.org/validator/)
    * [Jakarta Expression Language](https://jakarta.ee/specifications/expression-language/6.0/) / [Eclipse Expressly](https://github.com/eclipse-ee4j/expressly)
    * Testes de integração com [JUnit](https://junit.org/) e o  _plugin_  Maven  [maven-failsafe-plugin](https://maven.apache.org/surefire/maven-failsafe-plugin/)
    * Logging com [Simple Logging Framework for Java (slf4j)](https://www.slf4j.org/) e [Logback](https://logback.qos.ch/)
- ** Persistência:** [MySQL Server](https://www.mysql.com/products/community/)
- **Gerenciamento de Contêineres:** [Docker Desktop](https://docs.docker.com/desktop/setup/install/windows-install/) (opcional)

A persistência usa MySQL executado como um contêiner no Docker Deskop, mas pode ser usado com uma instância do servidor<br />
instalada localmente ou remotamente.