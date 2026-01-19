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


```
postgres=#  explain analyze select * from outbox where published_at is null order by id limit 10;
                                                                  QUERY PLAN                                                                  
----------------------------------------------------------------------------------------------------------------------------------------------
 Limit  (cost=0.28..0.77 rows=10 width=51) (actual time=0.086..0.089 rows=10 loops=1)
   ->  Index Scan using outbox_unpublished_id_idx on outbox  (cost=0.28..49.27 rows=1000 width=51) (actual time=0.084..0.086 rows=10 loops=1)
 Planning Time: 0.370 ms
 Execution Time: 0.155 ms
```