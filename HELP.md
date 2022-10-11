# Getting Started

### Documentação
Para referencia considerar as seguintes seções:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.4/maven-plugin/reference/html/)
* [Ferramenta de monitoramento de recursos](https://prometheus.io/docs/introduction/overview/)
* [Containerizção de aplicações](https://www.docker.com/)
* [Metricas e tracing de recursos](https://grafana.com/grafana/dashboards/)
* [Project Lombok is a java library](https://projectlombok.org/)
* [Java beans mapping](https://mapstruct.org/)

### Projeto

O projeto construindo com padrão REST para consumo de um banco de dados de filmes (MySQL), nao conta com implementacao 
das libs do spring cloud
(somente um rest template simples para detalhes do livros) e
spring security para protecao das apis. Projeto criado em 8 horas
de desenvolvimento.

### Build e desenvolvimento
Ferramentas e Sdks necessarias para build do projeto local.
Java11 ,Xamp e Docker (caso usar o docker password:root no aplication.yml linha 11)
na pasta raiz executar o comando: docker-compose up.

### Recursos

####Swagger
localhost:8080/swagger-ui.html

####MySQL
localhost:3306

####Actuator
localhost:8080/actuator/info
localhost:8080/actuator/metrics
localhost:8080/actuator/health
localhost:8080/actuator/prometheus

####Prometheus
localhost:9090

####Grafana
localhost:3000, user:admin, password:admin