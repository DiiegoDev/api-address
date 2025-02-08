# Stage 1: Development
FROM maven:3.9.9-eclipse-temurin-17 as development
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean test

# Stage 2: Build
FROM maven:3.9.9-eclipse-temurin-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 3: Production
FROM eclipse-temurin:17-jre-ubi9-minimal as production
WORKDIR /app
COPY --from=build app/target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]