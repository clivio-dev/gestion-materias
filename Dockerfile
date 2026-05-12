# Etapa de construcción (Build)
FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Etapa de ejecución (Run)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/gestorMateriasWeb-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
