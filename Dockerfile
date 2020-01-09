# Dockerfile for the backend app.

FROM openjdk:8-jre-alpine
VOLUME /tmp

# Copy our environment variables
COPY .env .env

# Copy the application runner
COPY run-app.sh run-app.sh

# Copy the application code/templates
COPY target/dependency/BOOT-INF/lib /app/lib
COPY target/dependency/META-INF /app/META-INF
COPY target/dependency/BOOT-INF/classes /app
COPY client/templates /app/templates

ENTRYPOINT ["sh", "run-app.sh"]