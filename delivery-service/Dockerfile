# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /workspace

# Copy all project files (use root as context in docker-compose)
COPY . .

# Build only the current module with dependencies
RUN ./mvnw clean package -pl delivery-service -am -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR
COPY --from=builder /workspace/delivery-service/target/*.jar app.jar

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]