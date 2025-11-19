# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

This is a Quarkus-based Java application designed for Knative deployments. The project follows a standard Quarkus REST application structure with Maven as the build tool.

**Key Technologies:**
- Quarkus 3.29.3 (Supersonic Subatomic Java Framework)
- Java 21
- Maven (with wrapper included)
- Jakarta REST (formerly JAX-RS)
- REST Assured for testing
- Docker for containerization

## Development Commands

### Running the Application

**Development mode (with live reload):**
```bash
./mvnw quarkus:dev
```
Dev UI available at: http://localhost:8080/q/dev/

**Production mode:**
```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

### Building

**Standard build:**
```bash
./mvnw clean package
```

**Build uber-jar (single fat JAR):**
```bash
./mvnw package -Dquarkus.package.jar.type=uber-jar
java -jar target/*-runner.jar
```

**Native executable (requires GraalVM):**
```bash
./mvnw package -Dnative
```

**Native executable (container-based, no GraalVM required):**
```bash
./mvnw package -Dnative -Dquarkus.native.container-build=true
./target/knative-affirmations-frontend-0.0.1-SNAPSHOT-runner
```

### Testing

**Run all tests:**
```bash
./mvnw test
```

**Run specific test class:**
```bash
./mvnw test -Dtest=GreetingResourceTest
```

**Run specific test method:**
```bash
./mvnw test -Dtest=GreetingResourceTest#testHelloEndpoint
```

**Integration tests:**
```bash
./mvnw verify
```

### Docker

**Build Docker image (JVM mode):**
```bash
docker build -f src/main/docker/Dockerfile.jvm -t knative-affirmations-frontend:latest .
```

Available Dockerfiles:
- `Dockerfile.jvm` - Standard JVM-based container
- `Dockerfile.legacy-jar` - Legacy uber-jar approach
- `Dockerfile.native` - Native executable container
- `Dockerfile.native-micro` - Minimal native container

## Code Architecture

### Package Structure

```
com.redhat.demos.knative
└── REST resources (JAX-RS endpoints)
```

**Base package:** `com.redhat.demos.knative`

### REST Endpoints

REST resources use Jakarta REST annotations:
- `@Path` - Defines the base URI path
- `@GET`, `@POST`, `@PUT`, `@DELETE` - HTTP method mappings
- `@Produces` - Response media type (e.g., `MediaType.TEXT_PLAIN`, `MediaType.APPLICATION_JSON`)
- `@Consumes` - Request media type

All REST resources should be placed in the base package or appropriate sub-packages.

### Configuration

Application configuration is managed through `src/main/resources/application.properties` (currently empty, using Quarkus defaults).

Quarkus supports:
- Property-based configuration
- Environment variable overrides (e.g., `QUARKUS_HTTP_PORT`)
- MicroProfile Config specification

### Testing Approach

Tests use:
- **JUnit 5** as the testing framework
- **@QuarkusTest** annotation for integration tests
- **REST Assured** for testing HTTP endpoints

Test pattern:
```java
@QuarkusTest
class ResourceTest {
    @Test
    void testEndpoint() {
        given()
          .when().get("/path")
          .then()
             .statusCode(200)
             .body(is("expected"));
    }
}
```

### CI/CD

The project uses GitHub Actions (`.github/workflows/ci.yaml`) for:
1. Building with Maven on push to `main`
2. Creating Docker images using `Dockerfile.jvm`
3. Pushing to Docker Hub (requires `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN` secrets)

Image tags:
- `<version>` (from pom.xml)
- `latest`

## Project Conventions

- **Java Version:** Java 21 (set in pom.xml)
- **Maven Wrapper:** Always use `./mvnw` instead of `mvn` for consistency
- **Encoding:** UTF-8 for all source and resource files
- **Dependency Injection:** CDI via Quarkus Arc (use `@Inject`, `@ApplicationScoped`, etc.)
- **JSON Serialization:** Jackson via `quarkus-rest-jackson`

## Knative Context

This is a frontend service intended for Knative deployment. When adding features:
- Consider scale-to-zero behavior
- Design for stateless operation
- Use environment variables for external service endpoints
- Health checks and readiness probes are handled by Quarkus defaults
