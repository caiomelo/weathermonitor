# Weather Monitor API

## Requirements

- Java 11+
- Docker

## How to run

1. Run a PostgreSQL instance on a docker container
   * `$ docker run --name postgres -d -e POSTGRES_DB=weathermonitor -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres`
2. Run the application from its root folder:
   * `$ ./gradlew bootRun`
3. Api documentation will be at http://localhost:8080/swagger-ui.html
