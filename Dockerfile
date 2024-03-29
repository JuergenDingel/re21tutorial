# syntax=docker/dockerfile:1
FROM openjdk:16-alpine
WORKDIR /app
COPY target/re-21-SHADED.jar .
CMD ["java", "-jar", "re-21-SHADED.jar"]