docker run --name my-postgres \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-e POSTGRES_DB=quarkus \
-p 5432:5432 \
-d postgres:latest

docker run --name my-redis -p 6379:6379 -d redis:latest
