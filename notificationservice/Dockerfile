# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven build output (jar file) into the container
COPY target/notificationservice-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application runs on
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
