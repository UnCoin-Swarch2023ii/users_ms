FROM maven:3.6.0-jdk-11-slim AS build
COPY src src
COPY pom.xml pom.xml
RUN mvn -f pom.xml clean package


FROM openjdk:17-jdk-alpine
COPY  target/Users-0.0.1-SNAPSHOT.jar user-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-app.jar"]