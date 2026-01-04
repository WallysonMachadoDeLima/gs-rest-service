# ---- Build Stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace/app
COPY . .
# Detect build tool and build accordingly
RUN if [ -f mvnw ]; then ./mvnw clean package -DskipTests; \
    elif [ -f gradlew ]; then ./gradlew build -x test; \
    else echo "No supported build tool found" && exit 1; fi

# ---- Run Stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
