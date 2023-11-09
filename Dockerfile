FROM maven:3.6.0-jdk-11-slim AS build
COPY src /app/src
COPY pom.xml /app/pom.xml
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/Users-0.0.1-SNAPSHOT.jar /app/user-app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-app.jar"]
