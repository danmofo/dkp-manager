# Dockerfile for the backend app.

FROM openjdk:8-jre-alpine
VOLUME /tmp

# Copy templates, these aren't included in the JAR file.
COPY client/templates /templates/

# Copy our environment variables
COPY .env .env

# Copy the application runner
COPY run-app.sh run-app.sh

# Copy the application code
COPY target/dependency/BOOT-INF/lib /app/lib
COPY target/dependency/META-INF /app/META-INF
COPY target/dependency/BOOT-INF/classes /app

ENTRYPOINT ["sh", "run-app.sh"]