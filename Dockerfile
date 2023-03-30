FROM maven:3.9.0-eclipse-temurin-8-alpine@sha256:9a82f83d4631bb8a77434ff6cce16f52996fbb3722fdd22baf3c05a6b0159e72 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:19-jdk-alpine3.16@sha256:a8ac92087e997ddbb21211a57eb0fcb02d10e81edbfb88c451cc021d7f407aae
COPY --from=build /app/target/TaskTiger-0.0.1-SNAPSHOT.jar /app/TaskTiger-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/app/Tasktiger-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
