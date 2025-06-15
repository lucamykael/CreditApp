FROM openjdk:23-slim AS build

RUN apt-get update && apt-get install -y maven && apt-get clean

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:23-jdk-slim AS runtime
WORKDIR /app
COPY --from=build /app/target/credit-0.0.1-SNAPSHOT.jar  app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]