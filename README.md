# Poke API

## Prerequisites

- JDK 19
- This project uses Lombok, so enable annotation processing in your IDE

## Technology Stack

- Language: [`Java 19`](https://www.java.com/)
- Compilation: [`Maven`](https://maven.apache.org/)
- Framework: [`SpringBoot`](https://spring.io/projects/spring-boot)
- Database: [`H2`](http://h2database.com/)
- Entity Graph: [`JPA Entity Graph`](https://cosium.github.io/making-jpa-great-again/)
- Open API Documentation: [`springdoc-openapi`](https://springdoc.org/)
- Boilerplate code reducer: [`Lombok`](https://projectlombok.org/)
- Logging: [`SLF4J`](https://www.slf4j.org/)
- Tests:
  - Unit Testing: [`JUnit5`](https://junit.org/junit5/docs/current/user-guide/)
  - Mocking: [`Mockito`](https://site.mockito.org/)
  - Mutation: Testing [`PIT Mutation Testing`](https://pitest.org/)
  - Code Coverage: [`Jacoco`](https://www.jacoco.org)
  - Architecture Testing: [`ArchUnit`](https://www.archunit.org/)

## Running the applications

```shell
sh ./run-application.sh
```

## Running the tests

```shell
sh ./run-test.sh
```

## Running the mutation tests

```shell
sh ./run-mutation-test.sh
```

## Kill the applications

```shell
sh ./kill-application.sh
```

## Available Endpoints

- Swagger UI: [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)
- H2 Console: [`http://localhost:8080/h2-console`](http://localhost:8080/h2-console)

## Schemas

```scheme
HttpResponseStatusDTO {
  timestamp       string($date-time)
  status          integer($int64)
  error           string
  message         string
  path            string
}
```

### Exception Responses

```yaml
Status Code: 400
Status Description: Bad Request
Body:
  {
    "timestamp": "2023-01-01T00:00:00.000Z",
    "status": 0,
    "error": "string",
    "message": "string",
    "path": "string",
  }
```

```yaml
Status Code: 404
Status Description: Not Found
Body:
  {
    "timestamp": "2023-01-01T00:00:00.000Z",
    "status": 0,
    "error": "string",
    "message": "string",
    "path": "string",
  }
```

```yaml
Status Code: 500
Status Description: Internal Server Error
Body:
  {
    "timestamp": "2023-01-01T00:00:00.000Z",
    "status": 0,
    "error": "string",
    "message": "string",
    "path": "string",
  }
```
