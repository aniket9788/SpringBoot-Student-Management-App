# Use a base image with OpenJDK
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/student-management-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your Spring Boot application will run
EXPOSE 8081

# Define the command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
