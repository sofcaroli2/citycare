FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /workspace

COPY . .
RUN mvn -f pom.xml -pl reports1 -am clean package -DskipTests

FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /workspace/reports1/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]