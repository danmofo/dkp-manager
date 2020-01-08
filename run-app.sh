#!/bin/sh 

# This script runs the Spring Boot app inside the container.

# Load environment variables
# https://stackoverflow.com/questions/19331497/set-environment-variables-from-file-of-key-value-pairs/19331521
set -o allexport
source .env
set +o allexport

# Wait for the database to become available.
until nc -z -v -w30 db 3306
do
  echo "Waiting for database connection... on host db"
  sleep 5
done

# Run the app
java -jar /app.jar