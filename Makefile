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

fw-info:
	docker-compose exec restservice ./mvnw flyway:info

fw-migrate:
	docker-compose exec restservice ./mvnw flyway:migrate

fw-clean:
	docker-compose exec restservice ./mvnw flyway:clean