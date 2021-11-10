# Coviwad - Documents

## Setup database for documents (vaccines and tests)

Run docker-compose configuration:

`docker compose up`

After the services have started, the database should be available thanks to the command : `docker exec -it covid-postgres-documents psql -U postgres`
Then connect to the database '\c documents-covid'

**If there is no table named "documents" : copy/paste the content of `./sql/create-tables.sql`**

## Launch microservice

Run bootRun with the given environment variables (in consignes.txt).
