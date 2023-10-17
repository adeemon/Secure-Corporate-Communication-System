FROM gradle:8.2.1-jdk17-alpine as builder
WORKDIR /app
COPY . /app/.
RUN ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/playground1-0.0.1-SNAPSHOT.jar /app/playground1-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/playground1-0.0.1-SNAPSHOT.jar"]
