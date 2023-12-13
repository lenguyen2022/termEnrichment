FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app

COPY mvnw .
RUN chmod +x ./mvnw
COPY .mvn .mvn
COPY pom.xml .
COPY src src
COPY src/main/resources/prod.application.properties src/main/resources/application.properties

RUN --mount=type=cache,target=/root/.m2 ./mvnw -f /app/pom.xml clean package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
CMD java -jar /app/target/GBADS_TermEnrichment-0.0.1-SNAPSHOT.jar
