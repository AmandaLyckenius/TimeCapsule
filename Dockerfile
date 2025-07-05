# Start from OpenJDK base image
FROM eclipse-temurin:22-jdk

# Set working directory
WORKDIR /app

# Copy built JAR file into container
COPY target/*.jar app.jar

# Expose the default port Spring Boot uses
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
