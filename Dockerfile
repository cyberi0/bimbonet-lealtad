FROM openjdk:11-jdk-slim

WORKDIR /app

COPY target/bimbonet-lealtad-0.0.1-SNAPSHOT.jar /app/bimbonet-lealtad-0.0.1-SNAPSHOT.jar

# Expose the application's port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/bimbonet-lealtad-0.0.1-SNAPSHOT.jar"]
