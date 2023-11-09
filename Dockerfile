FROM openjdk:17-jdk-alpine
COPY  target/Users-0.0.1-SNAPSHOT.jar user-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-app.jar"]