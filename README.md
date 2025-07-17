
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

## Running with Docker Compose

Start all services with ELK and OpenTelemetry collector:

```bash
docker-compose -f docker-compose.logging.yml -f docker-compose.otlp.yml up --build
```

> Note: Ensure Docker is running and ports 5601, 9200, 5001, and 4318 are available.

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

## Observability

- Access **Kibana**: [http://localhost:5601](http://localhost:5601)
- Access **Swagger UI** (per service): `http://localhost:<PORT>/swagger-ui.html`
- Logs are sent to Logstash via TCP (`port 5001`)
- Traces are exported using OTLP to OpenTelemetry Collector

## Configurations

- Central config repo: [config-files/](./config-files/)
- Swagger/OpenAPI: enabled via SpringDoc in each service
- OpenTelemetry Collector config: [otelcol/otel-collector-config.yml](./otelcol/otel-collector-config.yml)

## Security

- JWT-based auth
- Role-based access control (`@PreAuthorize`)
- Secured endpoints with Spring Security

## Future Enhancements

- Role-based UI with React/Angular frontend
- Kubernetes deployment scripts (Helm, Kustomize)
- Circuit breakers with Resilience4J
- Distributed caching with Hazelcast or Ignite

## Author

Developed by [Deepa Ganesh](https://github.com/deepa-ganesh)

---

> This project is a personal portfolio showcasing enterprise-grade backend system architecture using modern Spring ecosystem.
