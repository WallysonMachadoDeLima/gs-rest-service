up:
	docker-compose up -d

down:
	docker-compose down

build:
	docker-compose up --build

logs:
	docker-compose logs -f

test:
	docker-compose exec restservice ./mvnw test

flyway-info:
	docker-compose exec restservice ./mvnw flyway:info

flyway-migrate:
	docker-compose exec restservice ./mvnw flyway:migrate

flyway-clean:
	docker-compose exec restservice ./mvnw flyway:clean