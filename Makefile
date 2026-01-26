up:
	docker-compose up --build

# Gera migrations automáticas a partir das entidades JPA
generate-migration:
	./mvnw exec:java@generate-ddl