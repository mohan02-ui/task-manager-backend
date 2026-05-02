FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY backend/ .

RUN chmod +x mvnw
RUN ./mvnw clean package

CMD ["java", "-jar", "target/*.jar"]