# Dockerfile for the backend app.

FROM openjdk:8-jre-alpine
VOLUME /tmp

# Copy our environment variables
COPY .env .env

# Copy the application runner
COPY scripts/run-app.sh run-app.sh

# Copy the application code/templates - by using this approach builds are much quicker when only our code has changed.
# Depedencies
COPY target/dependency/BOOT-INF/lib /app/lib

# META-INF
COPY target/dependency/META-INF /app/META-INF

# Our application code/templates
COPY target/dependency/BOOT-INF/classes /app
COPY client/templates /app/templates

ENTRYPOINT ["sh", "run-app.sh"]