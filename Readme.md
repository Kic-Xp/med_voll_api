# Med Voll API

Este é um projeto de API para gerenciamento de uma clínica médica, desenvolvido em Java utilizando o framework Spring Boot. A API fornece funcionalidades para gerenciar médicos, pacientes, agendamentos e consultas.

## Estrutura do Projeto

- **/.classpath**: Arquivo de configuração do classpath do projeto.
- **/.gitignore**: Arquivo que especifica quais arquivos devem ser ignorados pelo Git.
- **/.project**: Arquivo de configuração do projeto.
- **/mvnw, /mvnw.cmd**: Scripts para executar o Maven Wrapper.
- **/pom.xml**: Arquivo de configuração do Maven, contendo as dependências do projeto.
- **/src/main**: Contém o código-fonte principal do projeto.
  - **/java/med/voll/api**: Pacote base da aplicação.
    - **/controller**: Contém os controladores REST.
    - **/domain**: Contém as classes de domínio e os repositórios.
    - **/infra**: Contém classes de infraestrutura, como configurações e serviços de segurança.
    - **/service**: Contém as classes de serviço.
- **/src/test**: Contém os testes do projeto.
- **/target**: Diretório gerado pelo Maven para armazenar os artefatos construídos.

## Funcionalidades

- **Gerenciamento de Médicos**: Cadastro, atualização, exclusão e listagem de médicos.
- **Gerenciamento de Pacientes**: Cadastro, atualização, exclusão e listagem de pacientes.
- **Agendamentos**: Agendamento de consultas médicas.
- **Consultas**: Gerenciamento de consultas realizadas.

## Tecnologias Usadas

- **Java 17**: Linguagem de programação principal do projeto.
- **Spring Boot**: Framework para facilitar a configuração e o desenvolvimento da aplicação.
- **Spring Data JPA**: Abstração para interações com o banco de dados.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Spring Security**: Framework para segurança da aplicação.
- **JWT (JSON Web Token)**: Para autenticação e autorização.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **Swagger**: Para documentação e teste da API.
- **JUnit**: Framework para testes unitários.
- **Mockito**: Framework para criação de mocks em testes unitários.

## Pré-requisitos

- Java 17
- Maven 3.8.1+

## Instalação

1. Clone o repositório:

    ```sh
    git clone https://github.com/seu-usuario/med_voll_api.git
    ```

2. Navegue até o diretório do projeto:

    ```sh
    cd med_voll_api
    ```

3. Compile o projeto e instale as dependências:

    ```sh
    ./mvnw clean install
    ```

## Configuração

### Banco de Dados

Este projeto utiliza o banco de dados H2 para desenvolvimento. A configuração do banco de dados pode ser encontrada no arquivo `src/main/resources/application.properties`.

## Executando a Aplicação

Para executar a aplicação, use o seguinte comando:

```sh
./mvnw spring-boot:run
```
A aplicação estará disponível em `http://localhost:8080`.
## Documentação da API

A documentação da API pode ser acessada através do Swagger, disponível em `http://localhost:8080/swagger-ui.html`.
Testes

Para executar os testes, utilize o seguinte comando:
```sh
./mvnw test
```
Agradecimentos

Este projeto foi desenvolvido com o apoio da Alura na Formação Spring Boot com Rodrigo Caneppele.
