# Demo Project for Itaú 

Um projeto de demonstração de uma API bancária com as funções:
- Cadastro de Clientes
- Listagem de Clientes
- Transferência entre Clientes

Tecnologias utilizadas:
 - Java 17
 - Spring Boot
 - Swagger (Open Api 3)
 - H2 in-memory database
   
 Arquitetura com código dividido em:
 - Models
 - Services
 - Controllers
 - Repositorys
  
Em Implementação:
- Testes Unitários
  
 Pré condições para a execução do projeto:
  - Instalação Java 17
  - Instalação Maven
  
Orientações para subir a aplicação (*considerando que o código já foi baixado e você está na pasta raiz do projeto*):
- Execute: mvn spring-boot:run
- Em um navegador verifique se a aplicação está de pé com o link: localhost:8080/health  
Se o retorno for “Ok” sua aplicação está de pé.
- Para acessar o Swagger vá para o link: http://localhost:8080/swagger-ui/index.html
- Para acessar o console do H2 siga as instruções que aparecem no terminal na inicialização do projeto. *A string de conexão com o banco é atualizada a cada nova execução da aplicação.*
  
  Referências:
- https://www.baeldung.com/spring-rest-openapi-documentation
- https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
- https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
- https://mari-azevedo.medium.com/construindo-uma-api-restful-com-java-e-spring-framework-46b74371d107
  
  Qualquer dúvida sinta-se à vontade para me contatar no LinkedIn -> https://www.linkedin.com/in/thalita-fernandes-carvalho
