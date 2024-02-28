FROM maven:3.8.1-openjdk-17-slim AS build

COPY . .

RUN mvn -f pom.xml clean package -Dmaven.test.skip

FROM openjdk:17.0.1-jdk-slim

COPY --from=build target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]