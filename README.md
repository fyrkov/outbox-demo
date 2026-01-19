# Demo project for the Outbox Pattern with Postgres

This repository is a small demo of the _Message Outbox Pattern_ setup with PostgreSQL and Spring Boot.
## How to run locally

### Dependencies
* JDK >= 21
* Docker

For running locally, start DB:
```bash
docker compose up -d
```
Fixed port 15433 is used which must be available!

Start the app:
```
./gradlew bootRun
```

Access the app at http://localhost:8080/


## Data model
 
## Notes

https://microservices.io/patterns/data/transactional-outbox.html

Spring offesr its own implemenatiaon