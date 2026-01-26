up:
	docker-compose up

down:
	docker-compose down

build:
	docker-compose up --build

migration:
	./mvnw exec:java@generate-ddl -Dexec.args="--config=hibernate.cfg.xml --output=src/main/resources/db/migration/V1__auto_schema.sql --format=true --delimiter=; --halt-on-error=true --create"