# ms-register

This is a Spring Boot microservice that provides a REST API for registering clients and conversions. The microservice uses Java and the Spring Boot framework, and has integration with a SQL database using Hibernate and Liquibase for managing database changes.

## Prerequisites
- Java 11 or later
- Gradle
- Docker

## Build the application
gradle build

## Build the Docker image

docker build -t ms-register:latest .

## Run the application in a Docker container

docker run -p 8080:8080 ms-register

## Docker Compose

Run the application using docker-compose

cd META-INF

docker-compose up

## Development

gradle bootRun

## Test

gradle test

## Dockerfile

FROM amazoncorretto:11

EXPOSE 8080

ADD build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

## API Docs

The API follows the OpenAPI specification version 3. The documentation can be accessed at the endpoint /v3/api-docs when the application is running.


## Endpoints

POST /register/client: Registers a new client

POST /register/conversion: Registers a new conversion


Please let me know if you have any questions or if there's anything else I can help with.
