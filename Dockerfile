# Dockerfile for the backend app.

FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY .env .env
COPY target/*.jar app.jar
COPY run-app.sh run-app.sh
# ENTRYPOINT ["java", "-jar", "/app.jar"]
ENTRYPOINT ["sh", "run-app.sh"]
# ENTRYPOINT ["tail", "-f", "run-app.sh"]