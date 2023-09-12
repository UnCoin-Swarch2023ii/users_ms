FROM ubuntu:latest

# Use an official Maven as a parent image
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project's pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Build the application using Maven
RUN mvn package

# Create a new image with the JAR file and OpenJDK
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the container
COPY --from=build /app/target/Users.jar ./app.jar

# Expose the application's port (adjust if necessary)
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]

ENTRYPOINT ["top", "-b"]