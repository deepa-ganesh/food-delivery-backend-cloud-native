
# Food Delivery Backend (Cloud Native)

A robust cloud-native food delivery backend system built using Spring Boot microservices architecture. It simulates a real-world scalable delivery platform with distributed tracing, centralized logging, secure communication, and dynamic service discovery.

## Features

- Microservice architecture with modular design
- Spring Cloud Gateway and Eureka Discovery Server
- Kafka-based asynchronous communication
- Redis for caching and distributed locks
- Centralized configuration using Spring Cloud Config
- ELK Stack for centralized logging
- OpenTelemetry + Micrometer Tracing with OTLP
- JWT-based authentication and role-based authorization
- Swagger/OpenAPI documentation
- Dockerized setup with docker-compose

## Microservices

| Module              | Description                                |
|---------------------|--------------------------------------------|
| `order-service`     | Handles customer orders                    |
| `restaurant-service`| Manages restaurant menu and status         |
| `delivery-service`  | Assigns delivery agents                    |
| `tracking-service`  | Tracks delivery progress                   |
| `notification-service` | Sends notifications on status changes  |
| `config-server`     | Externalized configuration provider        |
| `discovery-service` | Eureka-based service registry              |
| `gateway-service`   | API Gateway and route handler              |
| `common`            | Shared DTOs, exceptions, and utilities     |

## Environment Strategy

This project currently supports two environments:

- **Local Development** (`local` / default profile): Uses in-memory H2 database, runs services via `mvn spring-boot:run`, and relies on local Git configs.
- **Docker Environment** (`docker` profile): Uses Docker Compose to spin up services with PostgreSQL, Redis, Kafka, OpenTelemetry Collector, and ELK stack.

The environment-specific configuration is managed via Spring Profiles (`spring.profiles.active`) and centralized in the `config-files/` repository.

> **Note**: The current setup is designed to be easily extended to support additional environments such as `dev`, `qa`, `uat`, and `prod` using profile-specific YAMLs and externalized secrets/configs via vaults or CI/CD pipelines.

## Local vs Docker Environment

| Component             | Local Development                               | Docker Environment                                 |
|-----------------------|--------------------------------------------------|---------------------------------------------------|
| `Active Profile`      | `local` or default                               | `docker`                                          |
| `Database`            | H2 (in-memory, no setup needed)                 | PostgreSQL (`postgres` container)                |
| `Config Server`       | Local Git folder or manual URL                  | `config-server` with volume-mapped config repo   |
| `Discovery Server`    | Eureka at `http://localhost:8761`               | `discovery-service` container                    |
| `Redis`               | Optional / local Redis if needed                | `redis:7` container                               |
| `Kafka`               | Optional / local Kafka                          | `kafka` container                                 |
| `OpenTelemetry`       | Optional / Zipkin or none                       | `otel-collector:4318` with OTLP export           |
| `Config Import`       | `http://localhost:8888`                         | `http://config-server:8888`                      |
| `Env Variables`       | Handled by IntelliJ or shell                   | Provided via `.env` and `env_file:` in Compose   |
| `Startup Command`     | `mvn spring-boot:run` from module               | `docker-compose up --build`                      |
| `Build Tool`          | Maven build (`mvn clean install`)              | Multi-stage Docker build (`Dockerfile`) per module |
| `Logging`             | Console/File output                             | Centralized via Logstash (`localhost:5001`)      |
| `Tracing`             | Optional Zipkin                                 | OpenTelemetry -> OTLP Collector                  |
| `Ports`               | Exposed via `server.port` or IntelliJ config    | Mapped in `docker-compose.yml`

## Local Development

Build the entire project:

```bash
  mvn clean install
```

Run any service individually:

```bash
  cd order-service
  mvn spring-boot:run
```

## Running with Docker Compose

Start all services with ELK and OpenTelemetry collector:

```bash
  docker compose --env-file .env.docker -f docker-compose.yml up --build -d
```
Stop all services:
```bash
  docker compose down -v --remove-orphans   
```

> Note: Ensure Docker is running and ports mentioned in the docker-compose.yml are available.


## Observability

- Access **Kibana**: [http://localhost:5601](http://localhost:5601)
- Access **Swagger UI** (per service): `http://localhost:<PORT>/swagger-ui.html`
- Logs are sent to Logstash via TCP (`port 5001`)
- Traces are exported using OTLP to **OpenTelemetry** Collector
- Access Eureka **Service Discovery**: http://localhost:8761

## Configurations

- Central config repo: [config-files/](./config-files/)
- Swagger/OpenAPI: enabled via SpringDoc in each service
- OpenTelemetry Collector config: [otelcol/docker/otel-collector-config.yml](./otelcol/docker/otel-collector-config.yml)
- Logstash config: [logstash/docker/](./logstash/docker/)

## Security

- JWT-based auth
- Role-based access control (`@PreAuthorize`)
- Secured endpoints with Spring Security

## Rate Limiting

This project implements two types of rate limiting using Redis:

1. Spring Cloud Gateway – Built-in Redis Rate Limiter
   - Location: gateway-service
   - Description: Uses Spring Cloud Gateway’s built-in RedisRateLimiter to apply rate limits at the gateway layer, ensuring all incoming traffic is controlled before reaching internal microservices.
2. Custom Redis Rate Limiter
   - Location: order-service
   - Description: Implements a simple token bucket rate limiter using Redis to track request counts per IP/user over a time window.

Run Redis:

```bash
docker run --name redis -p 6379:6379 redis
```

## Future Enhancements

- Role-based UI with React/Angular frontend
- Kubernetes deployment scripts (Helm, Kustomize)
- Circuit breakers with Resilience4J
- Distributed caching with Hazelcast or Ignite

## Author

Developed by [Deepa Ganesh](https://github.com/deepa-ganesh)

---

> This project is a personal portfolio showcasing enterprise-grade backend system architecture using modern Spring ecosystem.
