FROM openjdk:8-alpine

COPY build/libs/Kotlin-Restfulapi-0.0.1-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar"]