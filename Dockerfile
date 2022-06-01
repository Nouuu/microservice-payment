FROM maven:3.8.5-eclipse-temurin-17-alpine as build
WORKDIR /app
COPY pom.xml .
COPY ./boissipay/ ./boissipay/
COPY ./specification/ ./specification/
RUN mvn clean install -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/boissipay/target/*.jar /app/boissipay.jar

EXPOSE 8080

ENV REDIS_HOST=localhost\
    REDIS_PORT=6379

CMD ["java", "-jar","-Dspring.profiles.active=env", "/app/boissipay.jar"]
