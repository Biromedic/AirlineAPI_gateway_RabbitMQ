# Base image
FROM openjdk:17-jdk-slim

# Application directory
WORKDIR /app

# Copy the application JAR
COPY target/gateway-0.0.1-SNAPSHOT.jar gateway.jar

# Expose port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "gateway.jar"]
